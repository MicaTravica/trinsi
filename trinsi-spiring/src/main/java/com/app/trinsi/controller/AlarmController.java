package com.app.trinsi.controller;

import com.app.trinsi.dto.HeartBeatTrackingDTO;
import com.app.trinsi.mapper.HeartBeatTrackingMapper;
import com.app.trinsi.model.Alarm;
import com.app.trinsi.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("trinsi/alarm")
public class AlarmController extends BaseController {

    private final AlarmService alarmService;

    @Autowired
    public AlarmController (AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<String> addHBT(@Valid @RequestBody HeartBeatTrackingDTO heartBeatTracking) {
        Alarm alarm = alarmService.heartBeatTracking(HeartBeatTrackingMapper.toHeartBeatTracking(heartBeatTracking));
        if (alarm == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        return new ResponseEntity<>(alarm.getMessage(), HttpStatus.OK);
    }
}
