package com.app.trinsi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(length = 3000)
    private String description;

    @Enumerated(EnumType.STRING)
    private EXERCISE_TYPE exerciseType;

    @Enumerated(EnumType.STRING)
    private INTENSITY intensity;

    @Enumerated(EnumType.STRING)
    private MUSCLES_GROUP musclesGroup;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Picture> pictures;

    public void update(Exercise updateExercise) {
        this.name = updateExercise.getName();
        this.description = updateExercise.getDescription();
        this.intensity = updateExercise.getIntensity();
        this.musclesGroup = updateExercise.getMusclesGroup();
        this.pictures = updateExercise.getPictures();
    }
}
