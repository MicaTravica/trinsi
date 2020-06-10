package com.app.trinsi.service.impl;

import com.app.trinsi.service.RulesService;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class RulesServiceImpl implements RulesService {

    private final KieServices kieServices;
    private final KieContainer kieContainer;

    @Autowired
    public RulesServiceImpl (KieServices kieServices, KieContainer kieContainer) {
        this.kieServices = kieServices;
        this.kieContainer = kieContainer;
    }

//    todo: zavrsiti
    @Override
    public void add(String drl) throws IOException {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        BufferedWriter writer = new BufferedWriter(
                new FileWriter("../trinsi-kjar/src/main/resources/com/app/trinsi/"
                        + UUID.randomUUID().toString() + ".drl"));
        writer.write(drl);
        writer.close();


    }
}
