package br.com.fiap.emotions.controller;

import br.com.fiap.emotions.dto.EmotionCreateDto;
import br.com.fiap.emotions.dto.EmotionUpdateDto;
import br.com.fiap.emotions.dto.EmotionViewDto;
import br.com.fiap.emotions.dto.EmotionViewRhScreenDto;
import br.com.fiap.emotions.service.EmotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class EmotionController {

    @Autowired()
    private EmotionService emotionService;

    @PostMapping("/emotions")
    @ResponseStatus(HttpStatus.CREATED)
    public EmotionViewDto create(@RequestBody @Valid EmotionCreateDto emotionCreateDto) {
        return emotionService.create(emotionCreateDto);
    }

    @PutMapping("/emotions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmotionViewDto update(@PathVariable String id, @RequestBody @Valid EmotionUpdateDto emotionUpdateDto) {
        return emotionService.update(id, emotionUpdateDto);
    }

    @GetMapping("/emotions/{deviceId}/today")
    @ResponseStatus(HttpStatus.OK)
    public List<EmotionViewDto> getEmotionsToday(@PathVariable String deviceId){
        return emotionService.getEmotionsToday(deviceId);
    }

    @GetMapping("/emotions/{yearMonth}")
    @ResponseStatus(HttpStatus.OK)
    public EmotionViewRhScreenDto getRhScreen(@PathVariable YearMonth yearMonth){
        return emotionService.getRhScreen(yearMonth);
    }
}