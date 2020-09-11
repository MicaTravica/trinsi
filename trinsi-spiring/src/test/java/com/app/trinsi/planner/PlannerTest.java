package com.app.trinsi.planner;

import com.app.trinsi.model.*;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlannerTest {

    @Test
    public void plannerNewOldNormalPrehypertensionActive() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("planner").setFocus();
        kieSession.insert(new UserHealth(1L, 65, 170, 70, 120,
                79, 55, 0, new Date(), false));
        QuestionnairePA questionnairePA = new QuestionnairePA(3, 80, 0,
                5, 30, 0, 5, 0, 0);
        kieSession.insert(questionnairePA);
        UserPlanner userPlanner = new UserPlanner();
        userPlanner.setMusclesGroupDates(new ArrayList<>());
        for (MUSCLES_GROUP m: MUSCLES_GROUP.values()) {
            userPlanner.addMusclesGroupDate(m);
        }
        kieSession.insert(userPlanner);
        int num = kieSession.fireAllRules();

        assertEquals(14, num);

        assertTrue(userPlanner.isOld());
        assertEquals(24, userPlanner.getBmi(), 0.5);
        assertEquals(NUTRITION_LEVEL.NORMAL, userPlanner.getNutritionLevel());
        assertEquals(BLOOD_PRESSURE_CLASSIFICATION.PREHYPERTENSION, userPlanner.getBloodPressureClassification());
        assertEquals(ACTIVITY_LEVEL.ACTIVE, userPlanner.getActivityLevel());
        assertEquals(INTENSITY.MODERATE, userPlanner.getIntensity());
        assertEquals(97, userPlanner.getLowerPulseLimit());
        assertEquals(118, userPlanner.getUpperPulseLimit());
        assertEquals(0, userPlanner.getNumIntensity());
        assertEquals(1, userPlanner.getSetsMuscular());
        assertEquals(60, userPlanner.getNumOfSecondsStretching());

        assertEquals(0, questionnairePA.getWalking());
        assertEquals(600, questionnairePA.getModerate());
        assertEquals(1920, questionnairePA.getVigorous());

        kieSession.getAgenda().getAgendaGroup("calculate").setFocus();
        userPlanner.setExercisesWarmUp(new ArrayList<>());
        userPlanner.setExercises(new ArrayList<>());
        userPlanner.setExercisesStretching(new ArrayList<>());
        kieSession.insert(userPlanner);
        insertData(kieSession);
        num = kieSession.fireAllRules();

        assertEquals(22, num);
        assertEquals(1, userPlanner.getNumIntensity());
        assertEquals(4, userPlanner.getNumOfExerciseMuscular());
        assertEquals(10, userPlanner.getRepetitionMuscular());
        assertEquals(20, userPlanner.getNumOfMinutesAerobic());
        assertEquals(MUSCLES_GROUP.ARMS, userPlanner.getMusclesGroup());
        assertEquals(1, userPlanner.getExercisesWarmUp().size());
        assertEquals( 1 + userPlanner.getNumOfExerciseMuscular(), userPlanner.getExercises().size());
        assertEquals(10, userPlanner.getExercisesStretching().size());
    }

    @Test
    public void plannerNewObesityHypertension1Inactive() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("planner").setFocus();
        kieSession.insert(new UserHealth(1L, 45, 170, 90, 140,
                82, 55, 0, new Date(), false));
        QuestionnairePA questionnairePA = new QuestionnairePA(0, 0, 0,
                3, 10, 0, 0, 0, 0);
        kieSession.insert(questionnairePA);
        UserPlanner userPlanner = new UserPlanner();
        userPlanner.setMusclesGroupDates(new ArrayList<>());
        for (MUSCLES_GROUP m: MUSCLES_GROUP.values()) {
            userPlanner.addMusclesGroupDate(m);
        }
        kieSession.insert(userPlanner);
        int num = kieSession.fireAllRules();

        assertEquals(14, num);

        assertFalse(userPlanner.isOld());
        assertEquals(31, userPlanner.getBmi(), 0.5);
        assertEquals(NUTRITION_LEVEL.OBESITY, userPlanner.getNutritionLevel());
        assertEquals(BLOOD_PRESSURE_CLASSIFICATION.HYPERTENSION1, userPlanner.getBloodPressureClassification());
        assertEquals(ACTIVITY_LEVEL.INACTIVE, userPlanner.getActivityLevel());
        assertEquals(INTENSITY.LIGHT, userPlanner.getIntensity());
        assertEquals(91, userPlanner.getLowerPulseLimit());
        assertEquals(103, userPlanner.getUpperPulseLimit());
        assertEquals(0, userPlanner.getNumIntensity());
        assertEquals(1, userPlanner.getSetsMuscular());
        assertEquals(30, userPlanner.getNumOfSecondsStretching());

        assertEquals(0, questionnairePA.getWalking());
        assertEquals(120, questionnairePA.getModerate());
        assertEquals(0, questionnairePA.getVigorous());

        kieSession.getAgenda().getAgendaGroup("calculate").setFocus();
        userPlanner.setExercisesWarmUp(new ArrayList<>());
        userPlanner.setExercises(new ArrayList<>());
        userPlanner.setExercisesStretching(new ArrayList<>());
        kieSession.insert(userPlanner);
        insertData(kieSession);
        num = kieSession.fireAllRules();

        assertEquals(16, num);
        assertEquals(1, userPlanner.getNumIntensity());
        assertEquals(0, userPlanner.getNumOfExerciseMuscular());
        assertEquals(10, userPlanner.getRepetitionMuscular());
        assertEquals(10, userPlanner.getNumOfMinutesAerobic());
        assertEquals(MUSCLES_GROUP.ARMS, userPlanner.getMusclesGroup());
        assertEquals(1, userPlanner.getExercisesWarmUp().size());
        assertEquals( 1 + userPlanner.getNumOfExerciseMuscular(), userPlanner.getExercises().size());
        assertEquals(7, userPlanner.getExercisesStretching().size());
    }

    @Test
    public void plannerUpdate() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("planner").setFocus();
        kieSession.insert(new UserHealth(1L, 65, 170, 70, 120,
                79, 55, 1500, new Date(), false));
        UserPlanner userPlanner = new UserPlanner();
        userPlanner.setIntensity(INTENSITY.MODERATE);
        userPlanner.setNumIntensity(10);
        List<MusclesGroupDate> musclesGroupDateList = new ArrayList<>();
        int i = 0;
        for (MUSCLES_GROUP m: MUSCLES_GROUP.values()) {
            musclesGroupDateList.add(new MusclesGroupDate(new Long(i), m , new GregorianCalendar(2014, Calendar.FEBRUARY, 1+ i).getTime()));
            i++;
        }
        userPlanner.setMusclesGroupDates(musclesGroupDateList);
        kieSession.insert(userPlanner);
        int num = kieSession.fireAllRules();

        assertEquals(10, num);

        assertTrue(userPlanner.isOld());
        assertEquals(24, userPlanner.getBmi(), 0.5);
        assertEquals(NUTRITION_LEVEL.NORMAL, userPlanner.getNutritionLevel());
        assertEquals(BLOOD_PRESSURE_CLASSIFICATION.PREHYPERTENSION, userPlanner.getBloodPressureClassification());
        assertEquals(ACTIVITY_LEVEL.ACTIVE, userPlanner.getActivityLevel());
        assertEquals(INTENSITY.MODERATE, userPlanner.getIntensity());
        assertEquals(97, userPlanner.getLowerPulseLimit());
        assertEquals(118, userPlanner.getUpperPulseLimit());
        assertEquals(10, userPlanner.getNumIntensity());
        assertEquals(1, userPlanner.getSetsMuscular());
        assertEquals(60, userPlanner.getNumOfSecondsStretching());

        kieSession.getAgenda().getAgendaGroup("calculate").setFocus();
        userPlanner.setExercisesWarmUp(new ArrayList<>());
        userPlanner.setExercises(new ArrayList<>());
        userPlanner.setExercisesStretching(new ArrayList<>());
        kieSession.insert(userPlanner);
        insertData(kieSession);
        num = kieSession.fireAllRules();

        assertEquals(22, num);
        assertEquals(11, userPlanner.getNumIntensity());
        assertEquals(4, userPlanner.getNumOfExerciseMuscular());
        assertEquals(13, userPlanner.getRepetitionMuscular());
        assertEquals(25, userPlanner.getNumOfMinutesAerobic());
        assertEquals(MUSCLES_GROUP.ARMS, userPlanner.getMusclesGroup());
        assertEquals(1, userPlanner.getExercisesWarmUp().size());
        assertEquals( 1 + userPlanner.getNumOfExerciseMuscular(), userPlanner.getExercises().size());
        assertEquals(10, userPlanner.getExercisesStretching().size());
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
        kieSession.insert(new Exercise(24L, "ime24", "opis", EXERCISE_TYPE.STRETCHES, null, MUSCLES_GROUP.BACK, new ArrayList<>()));
        kieSession.insert(new Exercise(25L, "ime25", "opis", EXERCISE_TYPE.STRETCHES, null, MUSCLES_GROUP.CHEST, new ArrayList<>()));
        kieSession.insert(new Exercise(26L, "ime26", "opis", EXERCISE_TYPE.STRETCHES, null, MUSCLES_GROUP.BUTTOCKS, new ArrayList<>()));
    }
}