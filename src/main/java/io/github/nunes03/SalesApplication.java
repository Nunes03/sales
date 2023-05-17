package io.github.nunes03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SalesApplication {

    @Value("${application.name}")
    private String applicationName;

    @Cachorro
    private Animal cachorro;

    @Gato
    private Animal gato;

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }

    @Bean(name = "executarAnimal")
    public CommandLineRunner executar() {
        return args -> gato.fazerBarulho();
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return applicationName;
    }
}