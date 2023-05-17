package io.github.nunes03.settings;

import io.github.nunes03.annotations.Development;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class Settings {

    @Bean(name = "executarDesenvolvimento")
    public CommandLineRunner execute() {
        return args -> System.err.println("Rodando configurações de desenvolvimento");
    }
}
