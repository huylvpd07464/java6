package module.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import module.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String> {

}
