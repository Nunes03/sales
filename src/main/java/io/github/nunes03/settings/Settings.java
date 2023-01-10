package io.github.nunes03.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Settings {

    @Bean(name = "applicationName")
    public String applicationName() {
        return "Sales system.";
    }
}
