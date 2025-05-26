package io.denchik.cinemakursach.service;

import io.denchik.cinemakursach.dto.RegistrationDto;
import io.denchik.cinemakursach.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(@NotEmpty String username);

    UserEntity findById(@NotEmpty Long id);

    List<RegistrationDto> findAllCommonUsers();

    void addAdmin(Long userId);

    void deleteUserById(@NotEmpty Long id);

    boolean authenticate(String username, String password);

}
