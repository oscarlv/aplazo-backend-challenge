package com.challenge.costumer;

import org.springframework.boot.SpringApplication;

public class TestCostumersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(CostumersServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
