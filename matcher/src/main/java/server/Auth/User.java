package server.Auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class User {
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[\\S]+$", message = "no spaces")
    private String username;
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[\\S]+$", message = "no spaces")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public User clone() {
        return new User(this.username, this.password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
