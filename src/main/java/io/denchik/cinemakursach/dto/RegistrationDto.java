package io.denchik.cinemakursach.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationDto {
    private Long id;
    @NotBlank
    @Size(min = 6, message = "Имя пользователя должно быть от 6 символов")
    @Pattern(
            regexp = "^[A-Za-zА-Яа-я\\d_-]+$",
            message = "Имя пользователя не должно содержать специальные символы"
    )
    private String username;
    @NotBlank(message = "Название мероприятия не должно быть пустым")
    private String email;
    @NotBlank
    @Size(min = 8, max = 20, message = "Пароль должен быть от 8 до 20 символов")
    @Pattern(
            regexp = "^(?=.*[A-ZА-Я])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-zА-Яа-я\\d@$!%*?&]+$",
            message = "Пароль должен содержать как минимум одну заглавную букву, одну цифру и один специальный символ (@$!%*?&)"
    )
    private String password;

    private Boolean lock;
}
