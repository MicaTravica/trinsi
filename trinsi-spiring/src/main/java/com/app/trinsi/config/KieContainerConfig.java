package com.app.trinsi.config;

import org.drools.core.ClockType;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KieContainerConfig {

    @Bean
    public KieServices kieServices() { return KieServices.Factory.get(); }

    @Bean
    public KieContainer kieContainer(KieServices kieServices) {
        KieContainer kieContainer = kieServices
                .newKieContainer(kieServices.newReleaseId("com.app.trinsi", "trinsi-kjar", "1.0.0-SNAPSHOT"));
        KieScanner kieScanner = kieServices.newKieScanner(kieContainer);
        kieScanner.start(10_000);
        return kieContainer;
    }

    @Bean
    public KieSession kieSession(KieServices kieServices, KieContainer kieContainer) {
        KieBaseConfiguration kieBaseConfiguration = kieServices.newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kieBaseConfiguration);
        KieSessionConfiguration kieSessionConfiguration = kieServices.newKieSessionConfiguration();
        kieSessionConfiguration.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kieSession = kieBase.newKieSession(kieSessionConfiguration, null);
        return kieSession;
    }

}
