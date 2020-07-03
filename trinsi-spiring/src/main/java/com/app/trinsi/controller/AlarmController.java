package com.app.trinsi.controller;

import com.app.trinsi.dto.ExerciseDTO;
import com.app.trinsi.dto.HeartBeatTrackingDTO;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.mapper.ExerciseMapper;
import com.app.trinsi.mapper.HeartBeatTrackingMapper;
import com.app.trinsi.model.Alarm;
import com.app.trinsi.model.HeartBeatTracking;
import com.app.trinsi.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> getExercise(@RequestBody HeartBeatTrackingDTO heartBeatTracking) {
        Alarm alarm = alarmService.heartBeatTracking(HeartBeatTrackingMapper.toHeartBeatTracking(heartBeatTracking));
        if (alarm == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        return new ResponseEntity<>(alarm.getMessage(), HttpStatus.OK);
    }
}
