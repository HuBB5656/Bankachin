package com.hubb.hijra.bankachin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BankachinApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankachinApplication.class, args);
    }

}
