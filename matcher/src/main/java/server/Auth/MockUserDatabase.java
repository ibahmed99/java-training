package server.Auth;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MockUserDatabase {

    ArrayList<User> users;

    public MockUserDatabase() {
        users = new ArrayList<User>();
        users.add(new User("johnsmith", "password"));
        users.add(new User("janedoe", "password2"));

    }

    public MockUserDatabase(ArrayList<User> users) {
        this.users = users;

    }

    public ArrayList<User> cloneUserList() {
        ArrayList<User> userListClone = new ArrayList<User>();
        this.users.forEach(user -> userListClone.add(user.clone()));
        return userListClone;
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return "MockUserDatabase{" +
                "users=" + users +
                '}';
    }


}
