package server.Auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import server.Auth.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);
}