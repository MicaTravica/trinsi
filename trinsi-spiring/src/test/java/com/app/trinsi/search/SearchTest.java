package com.app.trinsi.search;

import com.app.trinsi.model.*;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {

    @Test
    public void searchEmptyParams() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "", null, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(12, result.size());
    }

    @Test
    public void searchJustName() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "1", null, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(4, result.size());
        for (Exercise e : result) {
            assertTrue(e.getName().contains("1"));
        }
    }

    @Test
    public void searchJustExerciseType() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "", EXERCISE_TYPE.CARDIO, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(4, result.size());
        for (Exercise e : result) {
            assertTrue(e.getExerciseType() == EXERCISE_TYPE.CARDIO);
        }
    }

    @Test
    public void searchJustExerciseWeight() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "", null, CATEGORY.ADVANCED);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(7, result.size());
        for (Exercise e : result) {
            assertTrue(e.getExerciseWeight() == CATEGORY.ADVANCED);
        }
    }

    @Test
    public void searchNameAndExerciseType() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "1", EXERCISE_TYPE.WEIGHT_LOSS, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(3, result.size());
        for (Exercise e : result) {
            assertTrue(e.getName().contains("1"));
            assertTrue(e.getExerciseType() == EXERCISE_TYPE.WEIGHT_LOSS);
        }
    }

    @Test
    public void searchNameAndExerciseWeight() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "1", null, CATEGORY.ADVANCED);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(2, result.size());
        for (Exercise e : result) {
            assertTrue(e.getName().contains("1"));
            assertTrue(e.getExerciseWeight() == CATEGORY.ADVANCED);
        }
    }

    @Test
    public void searchExerciseTypeAndExerciseWeight() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "", EXERCISE_TYPE.CARDIO, CATEGORY.MIDDLE);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(1, result.size());
        for (Exercise e : result) {
            assertTrue(e.getExerciseType() == EXERCISE_TYPE.CARDIO);
            assertTrue(e.getExerciseWeight() == CATEGORY.MIDDLE);
        }
    }

    @Test
    public void searchAllParams() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "ime", EXERCISE_TYPE.STRENGTHS, CATEGORY.MIDDLE);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(1, result.size());
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
    }

    private void setParams(KieSession kieSession, String name, EXERCISE_TYPE exerciseType, CATEGORY exerciseWeight) {
        kieSession.setGlobal("gName", name);
        kieSession.setGlobal("gExerciseType", exerciseType);
        kieSession.setGlobal("gExerciseWeight", exerciseWeight);
        kieSession.setGlobal("exercises", new ArrayList<Exercise>());
    }
}
