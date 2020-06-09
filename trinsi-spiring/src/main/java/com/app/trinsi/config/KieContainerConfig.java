package com.app.trinsi.config;

import org.apache.maven.shared.invoker.*;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Collections;

@Configuration
public class KieContainerConfig {

    @Bean
    public KieServices kieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    public KieContainer kieContainer(KieServices kieServices) {
        KieContainer kieContainer = kieServices
                .newKieContainer(kieServices.newReleaseId("com.app.trinsi", "trinsi-kjar", "1.0.0-SNAPSHOT"));
        KieScanner kieScanner = kieServices.newKieScanner(kieContainer);
        kieScanner.start(10_000);
        return kieContainer;
    }

}
