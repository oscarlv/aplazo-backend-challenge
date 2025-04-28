package com.aplazo.challenge;

import org.springframework.boot.SpringApplication;

public class TestCostumersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(ChallengeServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
