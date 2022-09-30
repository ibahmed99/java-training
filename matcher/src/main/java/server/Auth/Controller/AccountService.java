package server.Auth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import server.Auth.InputCredentials;
import server.Auth.dao.AccountRepository;
import server.Auth.model.Account;

import java.util.List;

import static Hash.HashingFunction.hash;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account saveUser(InputCredentials input) {
        if (repository.findByUsername(input.getUsername()) != null) return null;
        Account actualAccount = Account.build(input.getUsername(), hash(input.getPassword()));

        return repository.save(actualAccount);
    }

    public List<Account> getAccounts() {

        return repository.findAll();
    }
}
