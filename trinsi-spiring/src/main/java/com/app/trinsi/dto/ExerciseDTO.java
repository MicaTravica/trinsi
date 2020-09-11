package com.app.trinsi.dto;

import com.app.trinsi.model.EXERCISE_TYPE;
import com.app.trinsi.model.INTENSITY;
import com.app.trinsi.model.MUSCLES_GROUP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {

    private Long id;

    @NotBlank(message = "Name can not be empty string")
    @NotNull(message = "Name can not be empty")
    private String name;

    @NotBlank(message = "Description can not be empty string")
    @NotNull(message = "Description can not be empty")
    private String description;

    @NotNull(message = "Exercise type can not be empty")
    private EXERCISE_TYPE exerciseType;

    private INTENSITY intensity;
    private MUSCLES_GROUP musclesGroup;
    @Valid
    private List<PictureDTO> pictures;

    @AssertTrue(message = "Intensity can not be empty")
    public boolean isIntensityValid() {
        if (this.exerciseType == EXERCISE_TYPE.AEROBIC && this.intensity == null) {
            return false;
        }
        return true;
    }

    @AssertTrue(message = "Muscles group can not be empty")
    public boolean isMusclesGroupValid() {
        if ((this.exerciseType == EXERCISE_TYPE.MUSCULAR || this.exerciseType == EXERCISE_TYPE.STRETCHES)
                && this.musclesGroup == null) {
            return false;
        }
        return true;
    }
}
