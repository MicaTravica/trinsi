package com.app.trinsi.alarms;

import com.app.trinsi.model.*;
import org.drools.core.ClassObjectFilter;
import org.drools.core.time.SessionPseudoClock;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlarmTest {

    @Test
    public void testAlarmBreatheDeeply() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepPseudoClock");
        SessionPseudoClock clock = kieSession.getSessionClock();

        Collection<Alarm> alarms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 95, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(0, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (int i = 0; i < 10; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 127, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(0, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (int i = 0; i < 10; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 127, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(1, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (Alarm a: alarms) {
            assertEquals("Breathe deeply", a.getMessage());
        }
        assertEquals(10, alarms.size());
        kieContainer.dispose();
    }

    @Test
    public void testAlarmBreak2Minutes() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepPseudoClock");
        SessionPseudoClock clock = kieSession.getSessionClock();

        Collection<Alarm> alarms = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 95, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(0, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (int i = 0; i < 5; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 133, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(0, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (int i = 0; i < 5; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 133, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(1, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (Alarm a: alarms) {
            assertEquals("Take a break of 2 minutes, walk", a.getMessage());
        }
        assertEquals(5, alarms.size());
        kieContainer.dispose();
    }
    @Test
    public void testAlarmBreak5Minutes() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepPseudoClock");
        SessionPseudoClock clock = kieSession.getSessionClock();

        Collection<Alarm> alarms = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 95, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(30, TimeUnit.SECONDS);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(0, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (int i = 0; i < 2; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 145, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(30, TimeUnit.SECONDS);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(0, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (int i = 0; i < 4; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 145, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(30, TimeUnit.SECONDS);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(1, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (Alarm a: alarms) {
            assertEquals("Take a break of 5 minutes, walk", a.getMessage());
        }
        assertEquals(4, alarms.size());
        kieContainer.dispose();
    }

    @Test
    public void testAlarmStopExercising() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepPseudoClock");
        SessionPseudoClock clock = kieSession.getSessionClock();

        Collection<Alarm> alarms = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 95, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(30, TimeUnit.SECONDS);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(0, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (int i = 0; i < 5; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 157, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(30, TimeUnit.SECONDS);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(1, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (Alarm a: alarms) {
            assertEquals("Stop exercising", a.getMessage());
        }
        assertEquals(5, alarms.size());
        kieContainer.dispose();
    }

    @Test
    public void testAlarmSpeedUp() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepPseudoClock");
        SessionPseudoClock clock = kieSession.getSessionClock();

        Collection<Alarm> alarms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 90, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();
            System.out.println(i);
            assertEquals(0, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (int i = 0; i < 1; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 89, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(0, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (int i = 0; i < 10; i++) {
            kieSession.insert(new HeartBeatTracking(1L, 89, 90, 120, BLOOD_PRESSURE_CLASSIFICATION.NORMAL, NUTRITION_LEVEL.NORMAL));
            clock.advanceTime(1, TimeUnit.MINUTES);
            int ruleCount = kieSession.fireAllRules();
            assertEquals(1, ruleCount);
            alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        }

        for (Alarm a: alarms) {
            assertEquals("Speed up", a.getMessage());
        }
        assertEquals(10, alarms.size());
        kieContainer.dispose();
    }
}
