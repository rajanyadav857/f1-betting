package com.example.f1betting;

import com.example.f1betting.model.entity.User;
import com.example.f1betting.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class F1BettingApplication {
    public static void main(String[] args) {
        SpringApplication.run(F1BettingApplication.class, args);
    }
}