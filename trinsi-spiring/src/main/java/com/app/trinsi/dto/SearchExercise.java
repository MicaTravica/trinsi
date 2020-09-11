package com.app.trinsi.dto;

import com.app.trinsi.model.EXERCISE_TYPE;
import com.app.trinsi.model.INTENSITY;
import com.app.trinsi.model.MUSCLES_GROUP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchExercise {

    private String name;
    private EXERCISE_TYPE exerciseType;
    private INTENSITY intensity;
    private MUSCLES_GROUP musclesGroup;
    
}
