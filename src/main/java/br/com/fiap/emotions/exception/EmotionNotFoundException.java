package br.com.fiap.emotions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmotionNotFoundException extends RuntimeException{

    public EmotionNotFoundException(String message){
        super(message);
    }
}
