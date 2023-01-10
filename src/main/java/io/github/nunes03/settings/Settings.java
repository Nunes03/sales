package io.github.nunes03.settings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
public class Settings {

    @Bean
    public CommandLineRunner execute() {
        return args -> {
            System.err.println("Runnn");
        };
    }
}
