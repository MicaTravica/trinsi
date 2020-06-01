package com.app.trinsi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Role(Role.Type.EVENT)
@Expires("15m")
public class HeartBeatTracking {

    private Long userId;
    private int currentPulse;
    private int targetPulse;
    private HEALTH_CONDITION healthCondition;

}
