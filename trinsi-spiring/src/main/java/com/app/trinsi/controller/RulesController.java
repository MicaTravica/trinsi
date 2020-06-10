package com.app.trinsi.controller;

import com.app.trinsi.dto.ExerciseDTO;
import com.app.trinsi.mapper.ExerciseMapper;
import com.app.trinsi.model.Exercise;
import com.app.trinsi.service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("trinsi/rules")
public class RulesController extends BaseController {

    private final RulesService rulesService;

    @Autowired
    public RulesController(RulesService rulesService) {
        this.rulesService = rulesService;
    }

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRules(@RequestBody String drl) throws IOException {
        System.out.println(drl);
        rulesService.add(drl);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}