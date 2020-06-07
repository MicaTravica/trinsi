package com.app.trinsi.reports;

import com.app.trinsi.model.*;
import org.drools.core.ClassObjectFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Collection;

public class ReportTest {

    @Test
    public void testAlarmBreatheDeeply() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ksession-reports");
        kieSession.insert(CATEGORY.BEGINNER);
        kieSession.insert(CATEGORY.MIDDLE);
        kieSession.insert(CATEGORY.ADVANCED);
        kieSession.insert(EXERCISE_TYPE.STRETCHES);
        kieSession.insert(EXERCISE_TYPE.STRENGTHS);
        kieSession.insert(EXERCISE_TYPE.CARDIO);
        kieSession.insert(EXERCISE_TYPE.WEIGHT_LOSS);
        kieSession.insert(new Exercise(1L, "ime", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.BEGINNER));
        kieSession.insert(new Exercise(1L, "ime", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.MIDDLE));
        kieSession.insert(new Exercise(1L, "ime", "opis", EXERCISE_TYPE.STRENGTHS, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(1L, "ime", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.ADVANCED));
        kieSession.insert(new UserPlanner(1L, CATEGORY.BEGINNER, 0,
                            null, null, 10, 0,
                            EXERCISE_TYPE.CARDIO, 0, null));
        kieSession.insert(new UserPlanner(1L, CATEGORY.BEGINNER, 0,
                null, null, 10, 0,
                EXERCISE_TYPE.WEIGHT_LOSS, 0, null));
        int ruleCount = kieSession.fireAllRules();
        System.out.println(ruleCount);

        Collection<MissingExercises> missingExercises =
                (Collection<MissingExercises>) kieSession.getObjects(new ClassObjectFilter(MissingExercises.class));
        for (MissingExercises me:missingExercises) {
            System.out.println(me.getCategory() + " " + me.getExerciseType() + " " + me.getNum());
        }
    }
}
