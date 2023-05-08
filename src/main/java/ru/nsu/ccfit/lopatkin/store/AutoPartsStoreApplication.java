package ru.nsu.ccfit.lopatkin.store;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class AutoPartsStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoPartsStoreApplication.class, args);
    }

}
