package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//Configuration File to Save the Changes to our DB
@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student ram = new Student(
                    "Rame",
                    "ram122@gmail.com",
                    LocalDate.of(1999, Month.JANUARY, 5)
            );

            Student shyam = new Student(
                    "Shyam",
                    "shyam16@gmail.com",
                    LocalDate.of(2000, Month.APRIL, 8)
            );
            Student yam = new Student(
                    "Yam",
                    "shyam16@gmail.com",
                    LocalDate.of(2001, Month.APRIL, 8)

            );

            //to save in our DB
            repository.saveAll(
                    List.of(ram, shyam,yam)
//                    List.of(ram)
            );
        };
    }
}
