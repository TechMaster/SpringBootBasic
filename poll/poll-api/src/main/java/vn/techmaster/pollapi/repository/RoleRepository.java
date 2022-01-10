package vn.techmaster.pollapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.pollapi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
