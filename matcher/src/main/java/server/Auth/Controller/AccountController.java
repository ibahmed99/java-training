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

    @PostMapping("/save")
    public String saveAccount(@RequestBody @Valid InputCredentials account) {
        accountService.saveUser(account);
        return "user registered";
    }

    @GetMapping("/get")
    public List<Account> getAccount() {

        return accountService.getAccount();
    }
}
