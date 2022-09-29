package server.Auth.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Account {
    @Id
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
}
