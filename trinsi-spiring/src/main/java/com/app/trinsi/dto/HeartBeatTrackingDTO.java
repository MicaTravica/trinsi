package com.app.trinsi.dto;

import com.app.trinsi.model.HEALTH_CONDITION;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeartBeatTrackingDTO {

    private Long userId;
    private int currentPulse;
    private int targetPulse;
    private HEALTH_CONDITION healthCondition;
}
