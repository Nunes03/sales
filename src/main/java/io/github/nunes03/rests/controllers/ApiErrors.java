package io.github.nunes03.rests.controllers;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> erros;

    public ApiErrors(String errorMessage) {
        this.erros = Arrays.asList(errorMessage);
    }
}
