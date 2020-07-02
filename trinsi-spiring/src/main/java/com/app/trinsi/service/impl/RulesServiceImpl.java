package com.app.trinsi.service.impl;

import com.app.trinsi.service.RulesService;
import org.apache.maven.shared.invoker.*;
import org.kie.api.builder.*;
import org.kie.api.io.ResourceType;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class RulesServiceImpl implements RulesService {

    private static String MAVEN_PATH = "C:\\Users\\travica\\Documents\\apache-maven-3.6.3\\";
    private static String POM_PATH ="../trinsi-kjar/pom.xml";
    private static String KJAR_PATH = "../trinsi-kjar/src/main/resources/";
    @Override
    public void add(String drl) throws IOException, MavenInvocationException {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            String errorMessages = "";
            for (Message message : messages) {
                System.out.println("Error: " + message.getText());
                errorMessages += message.getText() + "\n";
            }
            throw new IllegalStateException(errorMessages);
        }
        String packageName = drl.substring(drl.indexOf("package") + 7).trim();
        int indexSpace = (packageName.indexOf(" ") == -1) ? Integer.MAX_VALUE : packageName.indexOf(" ");
        int indexNewLine = (packageName.indexOf("\n") == -1) ? Integer.MAX_VALUE : packageName.indexOf("\n");
        int indexTab = (packageName.indexOf("\t") == -1) ? Integer.MAX_VALUE : packageName.indexOf("\t");
        int index = Math.min(indexSpace, Math.min(indexNewLine, indexTab));
        String[] packageList = packageName.substring(0, index).split(Pattern.quote("."));

        String fileName = KJAR_PATH;
        File directory;
        for (int i = 0; i < packageList.length; i++) {
            fileName += packageList[i] + "/";
            if (!(directory = new File(fileName)).exists()) {
                directory.mkdir();
            }
        }

        BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName + UUID.randomUUID().toString() + ".drl"));
        writer.write(drl);
        writer.close();

        InvocationRequest request = new DefaultInvocationRequest();
        File f = new File(POM_PATH);
        request.setPomFile(f);
        request.setGoals(Collections.singletonList("clean install"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(MAVEN_PATH));
        InvocationResult result = invoker.execute(request);

        if (result.getExitCode() != 0) {
            System.out.println(result.getExecutionException().toString());
            System.out.println(result.getExitCode());
        }
    }

}
