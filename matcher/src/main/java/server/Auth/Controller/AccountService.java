package server.Auth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import server.Auth.InputCredentials;
import server.Auth.dao.AccountRepository;
import server.Auth.model.Account;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account saveUser(InputCredentials input) {
        System.out.println(input.getUsername());
        System.out.println(input.getPassword());
        Account actualAccount = Account.build(input.getUsername(), input.getPassword());
        return repository.save(actualAccount);
    }

    public List<Account> getAccount() {

        return repository.findAll();
    }
}
