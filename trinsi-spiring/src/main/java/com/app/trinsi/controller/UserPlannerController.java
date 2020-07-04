package com.app.trinsi.controller;

import com.app.trinsi.dto.ReportDTO;
import com.app.trinsi.exceptions.MustUpdateHealthException;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.exceptions.UserNotFoundByUsernameException;
import com.app.trinsi.mapper.UserPlannerMapper;
import com.app.trinsi.model.MissingExercises;
import com.app.trinsi.model.User;
import com.app.trinsi.model.UserPlanner;
import com.app.trinsi.dto.UserPlannerDTO;
import com.app.trinsi.service.UserPlannerService;
import com.app.trinsi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("trinsi/planner")
public class UserPlannerController extends BaseController {

    private final UserPlannerService userPlannerService;
    private final UserService userService;

    @Autowired
    public UserPlannerController(UserPlannerService userPlannerService, UserService userService) {
        this.userPlannerService = userPlannerService;
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<UserPlannerDTO> getUserPlanner(Principal principal) throws UserNotFoundByUsernameException,
            ResourceNotFoundException, MustUpdateHealthException {
        User user = userService.findOneByUsername(principal.getName());
        if (user.getUserHealth() == null && user.getUserPlanner() == null)
            return new ResponseEntity<>(new UserPlannerDTO(), HttpStatus.OK);
        UserPlanner userPlanner = userPlannerService.findByUser(user);
        return new ResponseEntity<>(UserPlannerMapper.toDTO(userPlanner), HttpStatus.OK);
    }

    @PostMapping(value = "/reports", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> reports(@RequestBody ReportDTO params) {
        Collection<MissingExercises> result = userPlannerService.reports(params.getCategories(), params.getExerciseTypes());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
