package br.com.fiap.emotions.repository;

import br.com.fiap.emotions.model.Emotion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmotionRepository extends MongoRepository<Emotion, String> {
    List<Emotion> findByDeviceIdAndDate(String deviceId, LocalDate date);
    List<Emotion> findByDateBetween(LocalDate start, LocalDate end);
}
