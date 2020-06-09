package com.app.trinsi.planner;

import com.app.trinsi.model.*;
import org.drools.core.ClassObjectFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PlannerTest {

    @Test
    public void testAlarmBreatheDeeply() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup("planner").setFocus();
        kieSession.insert(new Exercise(1L, "ime", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.BEGINNER));
        kieSession.insert(new Exercise(1L, "ime", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.MIDDLE));
        kieSession.insert(new Exercise(1L, "ime", "opis", EXERCISE_TYPE.STRENGTHS, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(1L, "ime", "opis", EXERCISE_TYPE.WEIGHT_LOSS, CATEGORY.MIDDLE));
        kieSession.insert(new UserHealth(1L, GENDER.FEMALE, 23, 180,75,120,80,55,2, new Date(),false));
        UserPlanner userPlanner = new UserPlanner(null, CATEGORY.BEGINNER, 5, null,
                null, 0, 0, null, 0, new ArrayList<>());
        kieSession.insert(userPlanner);
        int ruleCount = kieSession.fireAllRules();
        System.out.println(ruleCount);
        System.out.println(userPlanner.getCategory());
        System.out.println(userPlanner.getNumCategory());
        System.out.println(userPlanner.getPhysicalCharacteristics());
        System.out.println(userPlanner.getHealthCondition());
        System.out.println(userPlanner.getNumOfExercise());
        System.out.println(userPlanner.getRepetition());
        System.out.println(userPlanner.getExerciseType());
        System.out.println(userPlanner.getTargetPulse());
        System.out.println(userPlanner.getExercises().size());
        System.out.println(userPlanner.getExercises());
    }
}
