package com.example.demo.beans;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            repository.saveAll(new ArrayList<Student>() {{
                add(new Student(
                        "Alex",
                        LocalDate.of(1998, 3, 23),
                        "alex@gmail.com"));
                add(new Student(
                        "Michael Scofield",
                        LocalDate.of(1999, 5, 3),
                        "scofield@gmail.com"));
                add(new Student(
                        "Mahone",
                        LocalDate.of(2000, 8, 2),
                        "mahone@gmail.com"));
            }});
        };
    }
}
