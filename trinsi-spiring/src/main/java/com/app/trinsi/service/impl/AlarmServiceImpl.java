package com.app.trinsi.service.impl;

import com.app.trinsi.model.Alarm;
import com.app.trinsi.model.HeartBeatTracking;
import com.app.trinsi.service.AlarmService;
import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

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
