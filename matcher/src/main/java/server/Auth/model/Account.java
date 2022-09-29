package server.Auth.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
}
