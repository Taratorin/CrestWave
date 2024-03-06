package ru.crestwavetech.crestwave;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CrestWaveApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CrestWaveApplication.class);
        application.run(args);
        log.info("Приложение успешно запущено");
    }
}