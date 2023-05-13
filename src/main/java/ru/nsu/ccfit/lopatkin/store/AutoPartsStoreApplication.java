package ru.nsu.ccfit.lopatkin.store;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
@EnableAsync
@EnableRetry
public class AutoPartsStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoPartsStoreApplication.class, args);
    }

}
