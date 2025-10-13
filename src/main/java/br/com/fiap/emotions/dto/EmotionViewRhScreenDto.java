package br.com.fiap.emotions.dto;

import br.com.fiap.emotions.model.EmotionEnum;

import java.util.Map;

public record EmotionViewRhScreenDto(EmotionEnum emotionPredominant, Map<EmotionEnum, String> emotionDistribution, int quantity) {
}

