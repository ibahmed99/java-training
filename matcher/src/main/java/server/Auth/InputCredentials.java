package server.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class InputCredentials {
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[\\S]+$", message = "no spaces")
    private String username;
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[\\S]+$", message = "no spaces")
    private String password;

    @Override
    public String toString() {
        return "UserCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
