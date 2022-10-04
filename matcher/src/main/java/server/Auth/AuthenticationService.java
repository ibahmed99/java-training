package server.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.Auth.dao.AccountRepository;
import server.Auth.model.Account;

import java.util.ArrayList;
import java.util.List;

import static Hash.HashingFunction.hash;

@Service
public class AuthenticationService {

    @Autowired
    private AccountRepository accountRepository;

//    public String authenticateUserCredentials(InputCredentials credentials) {
//        Account user = accountRepository.findByUsername(credentials.getUsername());
//        String hashedInputPassword = hash(credentials.getPassword());
//        if (user == null) return "User does not exist";
//        else if (user.getPassword().equals(credentials.getPassword())) return "" + user.hashCode();
//        else return "Invalid credentials";
//    }
}
