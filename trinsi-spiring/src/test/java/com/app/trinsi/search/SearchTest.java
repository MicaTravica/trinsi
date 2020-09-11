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
        setParams(kieSession, "", null, null, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(23, result.size());
    }

    @Test
    public void searchJustName() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "1", null, null, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(12, result.size());
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
        setParams(kieSession, "", EXERCISE_TYPE.AEROBIC, null, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(7, result.size());
        for (Exercise e : result) {
            assertTrue(e.getExerciseType() == EXERCISE_TYPE.AEROBIC);
        }
    }

    @Test
    public void searchJustIntensity() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "", null, INTENSITY.VIGOROUS, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(1, result.size());
        for (Exercise e : result) {
            assertTrue(e.getIntensity() == INTENSITY.VIGOROUS);
        }
    }

    @Test
    public void searchJustMusclesGroup() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "", null, null, MUSCLES_GROUP.ARMS);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(10, result.size());
        for (Exercise e : result) {
            assertTrue(e.getMusclesGroup() == MUSCLES_GROUP.ARMS);
        }
    }

    @Test
    public void searchNameAndExerciseType() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "1", EXERCISE_TYPE.MUSCULAR, null, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(5, result.size());
        for (Exercise e : result) {
            assertTrue(e.getName().contains("1"));
            assertTrue(e.getExerciseType() == EXERCISE_TYPE.MUSCULAR);
        }
    }

    @Test
    public void searchNameAndIntensity() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "3", null, INTENSITY.VIGOROUS, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(1, result.size());
        for (Exercise e : result) {
            assertTrue(e.getName().contains("3"));
            assertTrue(e.getIntensity() == INTENSITY.VIGOROUS);
        }
    }

    @Test
    public void searchNameAndMusclesGroup() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "1", null, null, MUSCLES_GROUP.ARMS);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(5, result.size());
        for (Exercise e : result) {
            assertTrue(e.getName().contains("1"));
            assertTrue(e.getMusclesGroup() == MUSCLES_GROUP.ARMS);
        }
    }

    @Test
    public void searchExerciseTypeAndIntensity() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "", EXERCISE_TYPE.AEROBIC, INTENSITY.MODERATE, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(3, result.size());
        for (Exercise e : result) {
            assertTrue(e.getExerciseType() == EXERCISE_TYPE.AEROBIC);
            assertTrue(e.getIntensity() == INTENSITY.MODERATE);
        }
    }

    @Test
    public void searchExerciseTypeAndMusclesGroup() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "", EXERCISE_TYPE.MUSCULAR, null, MUSCLES_GROUP.ARMS);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(6, result.size());
        for (Exercise e : result) {
            assertTrue(e.getExerciseType() == EXERCISE_TYPE.MUSCULAR);
            assertTrue(e.getMusclesGroup() == MUSCLES_GROUP.ARMS);
        }
    }

    @Test
    public void searchIntensityAndMusclesGroup() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "", null, INTENSITY.MODERATE, MUSCLES_GROUP.SHOULDERS);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(0, result.size());
    }

    @Test
    public void searchNameAndExerciseTypeAndIntensity() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "ime", EXERCISE_TYPE.AEROBIC, INTENSITY.MODERATE, null);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(3, result.size());
        for (Exercise e : result) {
            assertTrue(e.getName().contains("ime"));
            assertTrue(e.getExerciseType() == EXERCISE_TYPE.AEROBIC);
            assertTrue(e.getIntensity() == INTENSITY.MODERATE);
        }
    }

    @Test
    public void searchNameAndExerciseTypeAndMusclesGroup() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "ime", EXERCISE_TYPE.STRETCHES, null, MUSCLES_GROUP.LEGS);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(1, result.size());
        for (Exercise e : result) {
            assertTrue(e.getName().contains("ime"));
            assertTrue(e.getExerciseType() == EXERCISE_TYPE.STRETCHES);
            assertTrue(e.getMusclesGroup() == MUSCLES_GROUP.LEGS);
        }
    }

    @Test
    public void searchAllParams() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();

        insertData(kieSession);
        setParams(kieSession, "ime", EXERCISE_TYPE.MUSCULAR, INTENSITY.MODERATE, MUSCLES_GROUP.SHOULDERS);
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");

        assertEquals(0, result.size());
    }

    private void insertData(KieSession kieSession) {
        kieSession.insert(new Exercise(1L, "ime1", "opis", EXERCISE_TYPE.AEROBIC, INTENSITY.LIGHT, null, new ArrayList<>()));
        kieSession.insert(new Exercise(2L, "ime2", "opis", EXERCISE_TYPE.AEROBIC, INTENSITY.MODERATE, null, new ArrayList<>()));
        kieSession.insert(new Exercise(3L, "ime3", "opis", EXERCISE_TYPE.AEROBIC, INTENSITY.VIGOROUS, null, new ArrayList<>()));
        kieSession.insert(new Exercise(4L, "ime4", "opis", EXERCISE_TYPE.MUSCULAR, null, MUSCLES_GROUP.ARMS, new ArrayList<>()));
        kieSession.insert(new Exercise(5L, "ime5", "opis", EXERCISE_TYPE.MUSCULAR, null, MUSCLES_GROUP.SHOULDERS, new ArrayList<>()));
        kieSession.insert(new Exercise(6L, "ime6", "opis", EXERCISE_TYPE.MUSCULAR, null, MUSCLES_GROUP.ARMS, new ArrayList<>()));
        kieSession.insert(new Exercise(7L, "ime7", "opis", EXERCISE_TYPE.STRETCHES, null, MUSCLES_GROUP.ARMS, new ArrayList<>()));
        kieSession.insert(new Exercise(8L, "ime8", "opis", EXERCISE_TYPE.STRETCHES, null, MUSCLES_GROUP.ARMS, new ArrayList<>()));
        kieSession.insert(new Exercise(9L, "ime9", "opis", EXERCISE_TYPE.STRETCHES, null, MUSCLES_GROUP.ARMS, new ArrayList<>()));
        kieSession.insert(new Exercise(10L, "ime10", "opis", EXERCISE_TYPE.MUSCULAR, null, MUSCLES_GROUP.SHOULDERS, new ArrayList<>()));
        kieSession.insert(new Exercise(11L, "ime11", "opis", EXERCISE_TYPE.STRETCHES, null, MUSCLES_GROUP.ARMS, new ArrayList<>()));
        kieSession.insert(new Exercise(12L, "ime12", "opis", EXERCISE_TYPE.MUSCULAR, null, MUSCLES_GROUP.ARMS, new ArrayList<>()));
        kieSession.insert(new Exercise(13L, "ime13", "opis", EXERCISE_TYPE.AEROBIC, INTENSITY.LIGHT, null, new ArrayList<>()));
        kieSession.insert(new Exercise(14L, "ime14", "opis", EXERCISE_TYPE.AEROBIC, INTENSITY.MODERATE, null, new ArrayList<>()));
        kieSession.insert(new Exercise(15L, "ime15", "opis", EXERCISE_TYPE.AEROBIC, INTENSITY.LIGHT, null, new ArrayList<>()));
        kieSession.insert(new Exercise(16L, "ime16", "opis", EXERCISE_TYPE.AEROBIC, INTENSITY.MODERATE, null, new ArrayList<>()));
        kieSession.insert(new Exercise(17L, "ime17", "opis", EXERCISE_TYPE.MUSCULAR, null, MUSCLES_GROUP.ARMS, new ArrayList<>()));
        kieSession.insert(new Exercise(18L, "ime18", "opis", EXERCISE_TYPE.MUSCULAR, null, MUSCLES_GROUP.ARMS, new ArrayList<>()));
        kieSession.insert(new Exercise(19L, "ime19", "opis", EXERCISE_TYPE.MUSCULAR, null, MUSCLES_GROUP.ARMS, new ArrayList<>()));
        kieSession.insert(new Exercise(20L, "ime20", "opis", EXERCISE_TYPE.MUSCULAR, null, MUSCLES_GROUP.SHOULDERS, new ArrayList<>()));
        kieSession.insert(new Exercise(21L, "ime21", "opis", EXERCISE_TYPE.STRETCHES, null, MUSCLES_GROUP.LEGS, new ArrayList<>()));
        kieSession.insert(new Exercise(22L, "ime22", "opis", EXERCISE_TYPE.STRETCHES, null, MUSCLES_GROUP.ABDOMEN, new ArrayList<>()));
        kieSession.insert(new Exercise(23L, "ime23", "opis", EXERCISE_TYPE.STRETCHES, null, MUSCLES_GROUP.SHOULDERS, new ArrayList<>()));

    }

    private void setParams(KieSession kieSession, String name, EXERCISE_TYPE exerciseType, INTENSITY intensity,
                           MUSCLES_GROUP musclesGroup) {
        kieSession.setGlobal("gName", name);
        kieSession.setGlobal("gExerciseType", exerciseType);
        kieSession.setGlobal("gIntensity", intensity);
        kieSession.setGlobal("gMusclesGroup", musclesGroup);
        kieSession.setGlobal("exercises", new ArrayList<Exercise>());
    }
}
