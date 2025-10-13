package br.com.fiap.emotions.dto;

import br.com.fiap.emotions.model.Emotion;
import br.com.fiap.emotions.model.EmotionEnum;

import java.time.LocalDate;

public record EmotionViewDto(String id, String deviceId, LocalDate date, EmotionEnum emotion) {

    public EmotionViewDto(Emotion emotion){
        this(
                emotion.getId(),
                emotion.getDeviceId(),
                emotion.getDate(),
                emotion.getEmotion()
        );
    }
}

