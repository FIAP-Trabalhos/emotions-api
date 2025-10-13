package br.com.fiap.emotions.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "emotions")
public class Emotion {
    @Id
    private String id;
    private String deviceId;
    private LocalDate date;
    private EmotionEnum emotion;
}


