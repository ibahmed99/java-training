package server.Auth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.Auth.dao.UserRepository;
import server.Auth.model.Account;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/save")
    public String saveUser(@RequestBody Account account) {
        repository.save(account);
        return "user registered";
    }

    @GetMapping("/get")
    public List<Account> getUsers() {

        return repository.findAll();
    }
}
