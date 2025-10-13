package br.com.fiap.emotions.advice;

import br.com.fiap.emotions.exception.EmotionNotFoundException;
import br.com.fiap.emotions.exception.MaxEmotionsPerDayException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.rmi.UnexpectedException;
import java.util.*;

import org.springframework.security.access.AccessDeniedException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, List<String>> invalidArguments(MethodArgumentNotValidException error){
        Map<String, List<String>> mapError = new HashMap<>();
        List<FieldError> fields = error.getBindingResult().getFieldErrors();

        List<String> errors = new ArrayList<>();
        for (FieldError field : fields){
            errors.add(field.getDefaultMessage());
        }

        mapError.put("error", errors);  // chave "error" apontando para a lista
        return mapError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedTypeException.class)
    public Map<String, String> handleUnexpectedType(UnexpectedTypeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "JSON inválido ou malformado.");
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedException.class)
    public Map<String, String> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String, String> handleNotFound(NoSuchElementException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Recurso não encontrado.");
        return error;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, String> handleAccessDenied(AccessDeniedException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Acesso negado.");
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmotionNotFoundException.class)
    public Map<String, String> handleEmotionNotFound(EmotionNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> handleEmotionNotFound(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaxEmotionsPerDayException.class)
    public Map<String, String> handleEmotionNotFound(MaxEmotionsPerDayException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Erro interno no servidor.");
        return error;
    }
}