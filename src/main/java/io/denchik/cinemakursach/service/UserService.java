package io.denchik.cinemakursach.service;

import io.denchik.cinemakursach.dto.RegistrationDto;
import io.denchik.cinemakursach.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(@NotEmpty String email);

    UserEntity findByUsername(@NotEmpty String username);

    UserEntity findById(@NotEmpty Long id);

    List<UserEntity> findAllCommonUsers();

    void deleteUserById(@NotEmpty Long id);

}
