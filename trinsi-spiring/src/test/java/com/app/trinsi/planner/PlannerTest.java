package com.app.trinsi.planner;

import com.app.trinsi.model.*;
import org.drools.modelcompiler.ExecutableModelProject;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.internal.utils.KieService;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerTest {

    @Test
    public void plannerNew() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup("planner").setFocus();
        insertData(kieSession);

        kieSession.insert(new UserHealth(1L, GENDER.FEMALE, 23, 180, 75, 120, 80, 55, 2, new Date(), false));
        UserPlanner userPlanner = new UserPlanner();
        userPlanner.setExercises(new ArrayList<>());

        kieSession.insert(userPlanner);
        kieSession.fireAllRules();

        assertEquals(userPlanner.getCategory(), CATEGORY.MIDDLE);
        assertEquals(userPlanner.getPhysicalCharacteristics(), PHYSICAL_CHARACTERISTICS.SATISFACTORY);
        assertEquals(userPlanner.getHealthCondition(), HEALTH_CONDITION.GOOD);
        assertEquals(userPlanner.getNumOfExercise(), 5);
        assertEquals(userPlanner.getRepetition(), 11);
        assertEquals(userPlanner.getExerciseType(), EXERCISE_TYPE.CARDIO);
        assertEquals(userPlanner.getTargetPulse(), 154);
        assertEquals(userPlanner.getExercises().size(), userPlanner.getNumOfExercise());
    }

    @Test
    public void plannerUpdate() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup("planner").setFocus();

        insertData(kieSession);
        kieSession.insert(new UserHealth(1L, GENDER.FEMALE, 23, 180, 73, 120, 80, 55, 2, new Date(), false));
        UserPlanner userPlanner = new UserPlanner(1L, CATEGORY.BEGINNER, 5, PHYSICAL_CHARACTERISTICS.SKINNY,
                HEALTH_CONDITION.BAD, 3, 13, EXERCISE_TYPE.STRETCHES, 120, new ArrayList<>());
        kieSession.insert(userPlanner);
        kieSession.fireAllRules();

        assertEquals(userPlanner.getCategory(), CATEGORY.BEGINNER);
        assertEquals(userPlanner.getPhysicalCharacteristics(), PHYSICAL_CHARACTERISTICS.SATISFACTORY);
        assertEquals(userPlanner.getHealthCondition(), HEALTH_CONDITION.GOOD);
        assertEquals(userPlanner.getNumOfExercise(), 3);
        assertEquals(userPlanner.getRepetition(), 10);
        assertEquals(userPlanner.getExerciseType(), EXERCISE_TYPE.CARDIO);
        assertEquals(userPlanner.getTargetPulse(), 140);
        assertEquals(userPlanner.getExercises().size(), userPlanner.getNumOfExercise());

    }

    private void insertData(KieSession kieSession) {
        kieSession.insert(new Exercise(1L, "ime1", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.BEGINNER));
        kieSession.insert(new Exercise(2L, "ime2", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.MIDDLE));
        kieSession.insert(new Exercise(3L, "ime3", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(4L, "ime4", "opis", EXERCISE_TYPE.STRENGTHS, CATEGORY.BEGINNER));
        kieSession.insert(new Exercise(5L, "ime5", "opis", EXERCISE_TYPE.STRENGTHS, CATEGORY.MIDDLE));
        kieSession.insert(new Exercise(6L, "ime6", "opis", EXERCISE_TYPE.STRENGTHS, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(7L, "ime7", "opis", EXERCISE_TYPE.STRETCHES, CATEGORY.BEGINNER));
        kieSession.insert(new Exercise(8L, "ime8", "opis", EXERCISE_TYPE.STRETCHES, CATEGORY.MIDDLE));
        kieSession.insert(new Exercise(9L, "ime9", "opis", EXERCISE_TYPE.STRETCHES, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(10L, "ime10", "opis", EXERCISE_TYPE.WEIGHT_LOSS, CATEGORY.BEGINNER));
        kieSession.insert(new Exercise(11L, "ime11", "opis", EXERCISE_TYPE.WEIGHT_LOSS, CATEGORY.MIDDLE));
        kieSession.insert(new Exercise(12L, "ime12", "opis", EXERCISE_TYPE.WEIGHT_LOSS, CATEGORY.ADVANCED));
    }

    @Test
    public void add() {
        KieServices kieServices = KieServices.Factory.get();
        System.out.println(kieServices.getResources());
        System.out.println(kieServices.getRepository());
        String drl = "package com.app.trinsi.planner rule \"Cao\" salience 100 when then  System.out.println(\"CAO\"); end";
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

        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write( "src/main/resources/simple.drl",
                kieServices.getResources().newReaderResource( new StringReader(drl) ) );
        KieBuilder kieBuilder = kieServices.newKieBuilder( kfs ).buildAll();
        KieContainer kContainer = kieServices.newKieContainer(kieBuilder.getKieModule().getReleaseId());
        KieSession kieSession = kContainer.newKieSession();
        kieSession.fireAllRules();

//        PrintWriter printWriter = null;
//        try {
//            printWriter = new PrintWriter("src/main/resources/jbg.drl");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        printWriter.println(drl);
    }
}