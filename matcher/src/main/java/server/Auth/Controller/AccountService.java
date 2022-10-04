package server.Auth.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.Auth.InputCredentials;
import server.Auth.dao.AccountRepository;
import server.Auth.dao.RoleRepository;
import server.Auth.model.Account;
import server.Auth.model.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static Hash.HashingFunction.hash;

@Service
@Slf4j
@Transactional
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account saveUser(InputCredentials input) {
        if (accountRepository.findByUsername(input.getUsername()) != null) {
            log.error("user already exists");
            return null;
        }
        log.info("saving user: {} to database", input.getUsername());
        Account actualAccount = Account.build(input.getUsername(), passwordEncoder.encode(input.getPassword()), new ArrayList<Role>());
        actualAccount.getRoles().add(roleRepository.findByName("USER"));
        return accountRepository.save(actualAccount);
    }

    public void addRoleToUser(String username, String roleName) {
        Account user = accountRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        log.info("adding role: {} to user: {}", role, username);
        user.getRoles().add(role);
    }

    public Role saveRole(Role role) {
        log.info("saving role {} to database", role);
        return roleRepository.save(role);
    }

    public List<Account> getAccounts() {

        return accountRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByUsername(username);
        if (user == null) {
            log.error("user not found");
            throw new UsernameNotFoundException("User not found in database");
        } else {
            log.info("User found in database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
