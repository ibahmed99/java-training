package server.Auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import server.Auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
