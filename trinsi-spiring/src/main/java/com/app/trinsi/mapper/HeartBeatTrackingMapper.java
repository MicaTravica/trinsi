package com.app.trinsi.mapper;

import com.app.trinsi.dto.ExerciseDTO;
import com.app.trinsi.dto.HeartBeatTrackingDTO;
import com.app.trinsi.model.Exercise;
import com.app.trinsi.model.HeartBeatTracking;

public class HeartBeatTrackingMapper {

    public static HeartBeatTracking toHeartBeatTracking(HeartBeatTrackingDTO heartBeatTrackingDTO) {
        return new HeartBeatTracking(heartBeatTrackingDTO.getUserId(), heartBeatTrackingDTO.getCurrentPulse(),
                heartBeatTrackingDTO.getTargetPulse(), heartBeatTrackingDTO.getHealthCondition());
    }
}
