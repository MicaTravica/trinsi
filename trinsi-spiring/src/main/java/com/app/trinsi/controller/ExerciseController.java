package com.app.trinsi.controller;

import com.app.trinsi.dto.ExerciseDTO;
import com.app.trinsi.dto.SearchExercise;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.mapper.ExerciseMapper;
import com.app.trinsi.model.Exercise;
import com.app.trinsi.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("trinsi/exercise")
public class ExerciseController extends BaseController {

    private final ExerciseService exerciseService;

    @Autowired
    public  ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExerciseDTO> getExercise(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(ExerciseMapper.toDTO(exerciseService.findOneById(id)), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addExercise(@RequestBody ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseService.addExercise(ExerciseMapper.toExercise(exerciseDTO));
        return new ResponseEntity<>(exercise.getId(), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> updateExercise(@RequestBody ExerciseDTO exerciseDTO) throws ResourceNotFoundException {
        Exercise exercise = exerciseService.updateExercise(ExerciseMapper.toExercise(exerciseDTO));
        return new ResponseEntity<>(exercise.getId(), HttpStatus.OK);
    }

    @PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ExerciseDTO>> search(@RequestBody SearchExercise params) {
        Page<Exercise> result = exerciseService.search(params.getName(), params.getExerciseType(),
                params.getExerciseWeight(), params.getPageNum(), params.getSize());
        Page<ExerciseDTO> resultDTO = new PageImpl<>(
                result.get().map(ExerciseMapper::toDTO).collect(Collectors.toList()), result.getPageable(),
                result.getTotalElements());
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }
}
