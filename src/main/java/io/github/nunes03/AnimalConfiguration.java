package io.github.nunes03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {

    @Bean(name = "cachorro")
    public Animal cachorro() {
        return () -> System.err.println("Au Au");
    }

    @Bean(name = "gato")
    public Animal gato() {
        return () -> System.err.println("Miau");
    }
}
