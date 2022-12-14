package server.Auth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import server.Auth.InputCredentials;
import server.Auth.model.Account;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<Account> saveAccount(@RequestBody @Valid InputCredentials account) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/account/signup").toUriString());
        return ResponseEntity.created(uri).body(accountService.saveUser(account));
    }

    @GetMapping("/getAccounts")
    public List<Account> getAccounts() {
        List<Account> accounts = accountService.getAccounts();
        return accounts;
    }
}
