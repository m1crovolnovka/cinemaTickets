package io.denchik.cinemakursach.repository;

import io.denchik.cinemakursach.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserByEmail(String email);
    UserEntity findUserByUsername(String username);
    UserEntity findFirstByUsername(String username);
}
