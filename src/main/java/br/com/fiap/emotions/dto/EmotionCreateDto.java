package br.com.fiap.emotions.dto;

import br.com.fiap.emotions.model.EmotionEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record EmotionCreateDto(
        String id,

        @NotBlank(message = "O deviceId deve ser preenchido!")
        String deviceId,

        @NotNull(message = "A data deve ser preenchida!")
        @PastOrPresent(message = "A data não pode ser no futuro!")
        LocalDate date,

        @NotNull(message = "A emoção deve ser preenchida!")
        EmotionEnum emotion) {
}

