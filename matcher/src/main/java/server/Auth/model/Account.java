package server.Auth.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    String username;
    String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return username.equals(account.username) && password.equals(account.password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
