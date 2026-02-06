package com.systemgym.systemgym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //Habilita la funcionalidad de Scheduled Tasks o mas conocidos como Cron Jobs(Tareas programadas)
public class SystemGymApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemGymApplication.class, args);
    }

}
