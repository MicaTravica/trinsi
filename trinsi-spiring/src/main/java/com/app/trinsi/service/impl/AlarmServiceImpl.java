package com.app.trinsi.service.impl;

import com.app.trinsi.model.Alarm;
import com.app.trinsi.model.HEALTH_CONDITION;
import com.app.trinsi.model.HeartBeatTracking;
import com.app.trinsi.service.AlarmService;
import org.drools.core.ClassObjectFilter;
import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AlarmServiceImpl implements AlarmService {

    private final KieSession kieSession;

    @Autowired
    public AlarmServiceImpl (KieSession kieSession) {
        this.kieSession = kieSession;
    }

    @Override
    public Alarm heartBeatTracking(HeartBeatTracking heartBeatTracking) {
        ArrayList<Alarm> alarms = new ArrayList<>();
        kieSession.insert(heartBeatTracking);
        kieSession.fireAllRules();
        alarms.addAll((Collection<? extends Alarm>) kieSession.getObjects(new ClassObjectFilter(Alarm.class)));
        if (alarms.size() > 0)
            return alarms.get(0);
        return null;
    }
}
