package br.com.fiap.emotions.service;

import br.com.fiap.emotions.dto.EmotionCreateDto;
import br.com.fiap.emotions.dto.EmotionUpdateDto;
import br.com.fiap.emotions.dto.EmotionViewDto;
import br.com.fiap.emotions.dto.EmotionViewRhScreenDto;
import br.com.fiap.emotions.exception.EmotionNotFoundException;
import br.com.fiap.emotions.exception.MaxEmotionsPerDayException;
import br.com.fiap.emotions.model.Emotion;
import br.com.fiap.emotions.model.EmotionEnum;
import br.com.fiap.emotions.repository.EmotionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmotionService {

    @Autowired()
    private EmotionRepository emotionRepository;

    public EmotionViewDto create(EmotionCreateDto emotionCreateDto) {
        List<Emotion> emotions = emotionRepository.findByDeviceIdAndDate(emotionCreateDto.deviceId(), LocalDate.now());
        if (emotions.size() >= 2) {
            throw new MaxEmotionsPerDayException("Máximo de 2 emoções por dia por dispositivo!");
        }

        Emotion emotion = new Emotion();
        BeanUtils.copyProperties(emotionCreateDto, emotion);
        emotion.setDate(LocalDate.now());
        return new EmotionViewDto(emotionRepository.save(emotion));
    }

    public EmotionViewDto update(String id, EmotionUpdateDto emotionUpdateDto) {
        Emotion emotion = emotionRepository.findById(id)
                .orElseThrow(()-> new EmotionNotFoundException("Emoção não encontrada!"));

        if (emotionUpdateDto.deviceId() != "") {
            emotion.setDeviceId(emotionUpdateDto.deviceId());
        }

        if (emotionUpdateDto.date() != null) {
            emotion.setDate(emotionUpdateDto.date());
        }

        return new EmotionViewDto(emotionRepository.save(emotion));
    }

    public List<EmotionViewDto> getEmotionsToday(String deviceId) {
        return emotionRepository.findByDeviceIdAndDate(deviceId, LocalDate.now())
                .stream().
                map(EmotionViewDto::new)
                .toList();
    }

    public EmotionViewRhScreenDto getRhScreen(YearMonth yearMonth) {
        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();
        List<Emotion> emotions = emotionRepository.findByDateBetween(start, end);

        if (emotions.isEmpty()) {
            return new EmotionViewRhScreenDto(null, null, 0);
        }

        Map<EmotionEnum, Long> counts = emotions.stream()
                .collect(Collectors.groupingBy(Emotion::getEmotion, Collectors.counting()));

        EmotionEnum predominant = counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        int total = emotions.size();

        Map<EmotionEnum, String> distribution = new HashMap<>();
        counts.forEach((emotion, count) -> {
            double percent = (count * 100.0) / total;
            distribution.put(emotion, String.format("%.0f%%", percent));
        });

        return new EmotionViewRhScreenDto(predominant, distribution, total);
    }
}