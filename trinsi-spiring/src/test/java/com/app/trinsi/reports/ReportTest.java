package com.app.trinsi.reports;

import com.app.trinsi.model.*;
import org.drools.core.ClassObjectFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTest {

    @Test
    public void reportAllParams() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("reports").setFocus();

        kieSession.insert(CATEGORY.BEGINNER);
        kieSession.insert(CATEGORY.MIDDLE);
        kieSession.insert(CATEGORY.ADVANCED);
        kieSession.insert(EXERCISE_TYPE.STRETCHES);
        kieSession.insert(EXERCISE_TYPE.STRENGTHS);
        kieSession.insert(EXERCISE_TYPE.CARDIO);
        kieSession.insert(EXERCISE_TYPE.WEIGHT_LOSS);

        insertData(kieSession);
        kieSession.fireAllRules();

        Collection<MissingExercises> missingExercises =
                (Collection<MissingExercises>) kieSession.getObjects(new ClassObjectFilter(MissingExercises.class));

        assertEquals(12, missingExercises.size());
        for (MissingExercises me : missingExercises) {
            if (me.getCategory() == CATEGORY.BEGINNER && me.getExerciseType() == EXERCISE_TYPE.CARDIO) {
                assertEquals(5, me.getNum());
            } else if (me.getCategory() == CATEGORY.BEGINNER && me.getExerciseType() == EXERCISE_TYPE.WEIGHT_LOSS) {
                assertEquals(9, me.getNum());
            } else if (me.getCategory() == CATEGORY.MIDDLE && me.getExerciseType() == EXERCISE_TYPE.WEIGHT_LOSS) {
                assertEquals(3, me.getNum());
            } else {
                assertEquals(0, me.getNum());
            }
        }
    }

    @Test
    public void reportOneCategory() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("reports").setFocus();

        kieSession.insert(CATEGORY.BEGINNER);
        kieSession.insert(EXERCISE_TYPE.STRETCHES);
        kieSession.insert(EXERCISE_TYPE.STRENGTHS);
        kieSession.insert(EXERCISE_TYPE.CARDIO);
        kieSession.insert(EXERCISE_TYPE.WEIGHT_LOSS);

        insertData(kieSession);
        kieSession.fireAllRules();

        Collection<MissingExercises> missingExercises =
                (Collection<MissingExercises>) kieSession.getObjects(new ClassObjectFilter(MissingExercises.class));

        assertEquals(4, missingExercises.size());
        for (MissingExercises me : missingExercises) {
            if (me.getCategory() == CATEGORY.BEGINNER && me.getExerciseType() == EXERCISE_TYPE.CARDIO) {
                assertEquals(5, me.getNum());
            } else if (me.getCategory() == CATEGORY.BEGINNER && me.getExerciseType() == EXERCISE_TYPE.WEIGHT_LOSS) {
                assertEquals(9, me.getNum());
            } else {
                assertEquals(0, me.getNum());
            }
        }
    }

    @Test
    public void reportOneExerciseType() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("reports").setFocus();

        kieSession.insert(CATEGORY.BEGINNER);
        kieSession.insert(CATEGORY.MIDDLE);
        kieSession.insert(CATEGORY.ADVANCED);
        kieSession.insert(EXERCISE_TYPE.WEIGHT_LOSS);

        insertData(kieSession);
        kieSession.fireAllRules();

        Collection<MissingExercises> missingExercises =
                (Collection<MissingExercises>) kieSession.getObjects(new ClassObjectFilter(MissingExercises.class));

        assertEquals(3, missingExercises.size());
        for (MissingExercises me : missingExercises) {
            if (me.getCategory() == CATEGORY.BEGINNER && me.getExerciseType() == EXERCISE_TYPE.WEIGHT_LOSS) {
                assertEquals(9, me.getNum());
            } else if (me.getCategory() == CATEGORY.MIDDLE && me.getExerciseType() == EXERCISE_TYPE.WEIGHT_LOSS) {
                assertEquals(3, me.getNum());
            } else {
                assertEquals(0, me.getNum());
            }
        }
    }

    @Test
    public void reportNoParams() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("reports").setFocus();

        insertData(kieSession);
        kieSession.fireAllRules();

        Collection<MissingExercises> missingExercises =
                (Collection<MissingExercises>) kieSession.getObjects(new ClassObjectFilter(MissingExercises.class));

        assertEquals(0, missingExercises.size());
    }

    @Test
    public void reportMixedParams() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("reports").setFocus();

        kieSession.insert(CATEGORY.BEGINNER);
        kieSession.insert(CATEGORY.ADVANCED);
        kieSession.insert(EXERCISE_TYPE.CARDIO);
        kieSession.insert(EXERCISE_TYPE.WEIGHT_LOSS);

        insertData(kieSession);
        kieSession.fireAllRules();

        Collection<MissingExercises> missingExercises =
                (Collection<MissingExercises>) kieSession.getObjects(new ClassObjectFilter(MissingExercises.class));

        assertEquals(4, missingExercises.size());
        for (MissingExercises me : missingExercises) {
            if (me.getCategory() == CATEGORY.BEGINNER && me.getExerciseType() == EXERCISE_TYPE.CARDIO) {
                assertEquals(5, me.getNum());
            } else if (me.getCategory() == CATEGORY.BEGINNER && me.getExerciseType() == EXERCISE_TYPE.WEIGHT_LOSS) {
                assertEquals(9, me.getNum());
            } else {
                assertEquals(0, me.getNum());
            }
        }
    }


    private void insertData(KieSession kieSession) {
        kieSession.insert(new Exercise(1L, "ime1", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.BEGINNER));
        kieSession.insert(new Exercise(2L, "ime2", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.BEGINNER));
        kieSession.insert(new Exercise(3L, "ime3", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.MIDDLE));
        kieSession.insert(new Exercise(4L, "ime4", "opis", EXERCISE_TYPE.CARDIO, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(5L, "ime5", "opis", EXERCISE_TYPE.STRENGTHS, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(6L, "ime6", "opis", EXERCISE_TYPE.STRETCHES, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(7L, "ime7", "opis", EXERCISE_TYPE.STRENGTHS, CATEGORY.MIDDLE));
        kieSession.insert(new Exercise(8L, "ime8", "opis", EXERCISE_TYPE.WEIGHT_LOSS, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(9L, "ime9", "opis", EXERCISE_TYPE.WEIGHT_LOSS, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(10L, "ime10", "opis", EXERCISE_TYPE.WEIGHT_LOSS, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(11L, "ime11", "opis", EXERCISE_TYPE.WEIGHT_LOSS, CATEGORY.ADVANCED));
        kieSession.insert(new Exercise(12L, "ime12", "opis", EXERCISE_TYPE.WEIGHT_LOSS, CATEGORY.BEGINNER));
        kieSession.insert(new UserPlanner(1L, CATEGORY.BEGINNER, 0,
                null, null, 7, 0,
                EXERCISE_TYPE.CARDIO, 0, new ArrayList<>()));
        kieSession.insert(new UserPlanner(2L, CATEGORY.BEGINNER, 0,
                null, null, 10, 0,
                EXERCISE_TYPE.WEIGHT_LOSS, 0, new ArrayList<>()));
        kieSession.insert(new UserPlanner(3L, CATEGORY.BEGINNER, 0,
                null, null, 7, 0,
                EXERCISE_TYPE.WEIGHT_LOSS, 0, new ArrayList<>()));
        kieSession.insert(new UserPlanner(3L, CATEGORY.MIDDLE, 0,
                null, null, 3, 0,
                EXERCISE_TYPE.WEIGHT_LOSS, 0, new ArrayList<>()));
    }
}
