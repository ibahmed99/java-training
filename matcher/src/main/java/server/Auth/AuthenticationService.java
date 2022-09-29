package server.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService {

    @Autowired
    private MockUserDatabase mockUserDatabase;

    public String authenticateUserCredentials(InputCredentials credentials) {
        ArrayList<User> userList = mockUserDatabase.cloneUserList();
        userList.removeIf(user -> !user.getUsername().equals(credentials.getUsername()) || !user.getPassword().equals(credentials.getPassword()));
        if (userList.size() == 1) return "" + userList.get(0).hashCode();
        else return "Invalid credentials";
    }
}
