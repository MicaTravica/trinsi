package com.app.trinsi.dto;

import com.app.trinsi.model.CATEGORY;
import com.app.trinsi.model.EXERCISE_TYPE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {

    private Long id;
    private String name;
    private String description;
    private EXERCISE_TYPE exerciseType;
    private CATEGORY exerciseWeight;

}
