package io.denchik.cinemakursach.repository;


import io.denchik.cinemakursach.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
