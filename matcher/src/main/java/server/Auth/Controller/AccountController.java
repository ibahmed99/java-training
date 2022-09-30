package server.Auth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.Auth.InputCredentials;
import server.Auth.dao.AccountRepository;
import server.Auth.model.Account;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/signup")
    public String saveAccount(@RequestBody @Valid InputCredentials account) {
        Account newAccount = accountService.saveUser(account);
        if (newAccount == null) return "user already exists";
        return "user registered";
    }

    @GetMapping("/get")
    public List<Account> getAccounts() {
        List<Account> accounts = accountService.getAccounts();
        return accounts;
    }
}
