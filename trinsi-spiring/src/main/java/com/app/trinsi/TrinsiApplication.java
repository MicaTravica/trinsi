package com.app.trinsi;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TrinsiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrinsiApplication.class, args);
    }

}
