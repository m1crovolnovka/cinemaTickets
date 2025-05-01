package io.denchik.cinemakursach.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    @NotNull(message = "Номер карты не должен быть пустым")
    @Size(min = 16, max = 16, message = "Номер карты состоит из 16 цифр")
    @Pattern(
            regexp = "^[\\d]+$",
            message = "Номер карты состоит из цифр"
    )
    String cardNumber;
    @NotBlank(message = "Имя держателя карты не должно быть пустым")
    @Pattern(
            regexp = "^[A-Z ]+$",
            message = "Имя держателя карты состоит только из латинских и строчных букв"
    )
    String cardholderName;

    @NotNull(message = "Дата истечения не должна быть пустой")
    YearMonth date;
    @NotNull(message = "CVV не должен быть пустым")
    @Size(min = 3, max = 3, message = "CVV состоит из 3 цифр")
    @Pattern(
            regexp = "^[\\d]+$",
            message = "CVV карты состоит из цифр"
    )
    String CVV;
}
