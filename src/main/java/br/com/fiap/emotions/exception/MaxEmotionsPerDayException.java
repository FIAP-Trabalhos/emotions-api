package br.com.fiap.emotions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaxEmotionsPerDayException extends RuntimeException {
    public MaxEmotionsPerDayException(String message) {
        super(message);
    }
}
