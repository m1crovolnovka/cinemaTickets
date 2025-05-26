package io.denchik.cinemakursach.mapper;

import io.denchik.cinemakursach.dto.RegistrationDto;
import io.denchik.cinemakursach.dto.TicketDto;
import io.denchik.cinemakursach.models.Ticket;
import io.denchik.cinemakursach.models.UserEntity;

public class UserMapper {

    public static RegistrationDto mapToRegistrationDto(UserEntity userEntity) {
        RegistrationDto registrationDto = RegistrationDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .lock(userEntity.getLock())
                .build();
        return registrationDto;
    }
}
