package server.Auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import server.Auth.model.Account;

public interface UserRepository extends JpaRepository<Account, Integer> {

}