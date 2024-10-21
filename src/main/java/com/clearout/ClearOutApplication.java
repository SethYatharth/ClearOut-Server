package com.clearout;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClearOutApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClearOutApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(

    ) {
        return args -> {

        };
    }

}
