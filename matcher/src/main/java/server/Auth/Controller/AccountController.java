package server.Auth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.Auth.dao.AccountRepository;
import server.Auth.model.Account;

import java.util.List;

@RestController
@RequestMapping(path = "account")
public class AccountController {

    @Autowired
    private AccountRepository repository;

    @PostMapping("/save")
    public String saveAccount(@RequestBody Account account) {
        repository.save(account);
        return "user registered";
    }

    @GetMapping("/get")
    public List<Account> getAccount() {

        return repository.findAll();
    }
}
