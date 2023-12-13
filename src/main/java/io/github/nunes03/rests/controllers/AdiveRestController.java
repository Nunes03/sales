package io.github.nunes03.rests.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class AdiveRestController {

    @ExceptionHandler(value = ResponseStatusException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrors handlerResponseStatusException(ResponseStatusException responseStatusException) {
        return new ApiErrors(
            responseStatusException.getMessage()
        );
    }
}
