package server.Auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class InputCredentials {
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[\\S]+$", message = "no spaces")
    String username;
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[\\S]+$", message = "no spaces")
    String password;

    public InputCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputCredentials)) return false;
        InputCredentials that = (InputCredentials) o;
        return username.equals(that.username) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
