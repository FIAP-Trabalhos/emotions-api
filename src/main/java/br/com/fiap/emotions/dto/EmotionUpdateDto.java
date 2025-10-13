package br.com.fiap.emotions.dto;

import br.com.fiap.emotions.model.EmotionEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record EmotionUpdateDto(
        String deviceId,

        @PastOrPresent(message = "A data não pode ser no futuro!")
        LocalDate date,

        @NotNull(message = "A emoção deve ser preenchida!")
        EmotionEnum emotion) {
}

