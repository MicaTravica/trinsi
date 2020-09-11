package com.app.trinsi.controller;

import com.app.trinsi.dto.UserHealthDTO;
import com.app.trinsi.exceptions.ResourceCantUpdateException;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.exceptions.UserNotFoundByUsernameException;
import com.app.trinsi.mapper.QuestionnaireMapper;
import com.app.trinsi.mapper.UserHealthMapper;
import com.app.trinsi.model.QuestionnairePA;
import com.app.trinsi.model.User;
import com.app.trinsi.model.UserHealth;
import com.app.trinsi.service.UserHealthService;
import com.app.trinsi.service.UserPlannerService;
import com.app.trinsi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("trinsi/health")
public class UserHealthController extends BaseController {

    private final UserHealthService userHealthService;
    private final UserService userService;
    private final UserPlannerService userPlannerService;

    @Autowired
    public UserHealthController(UserHealthService userHealthService, UserService userService,
                                UserPlannerService userPlannerService){
        this.userHealthService  = userHealthService;
        this.userService = userService;
        this.userPlannerService = userPlannerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<UserHealthDTO> getUserHealth(Principal principal) throws UserNotFoundByUsernameException {
        User user = userService.findOneByUsername(principal.getName());
        if (user.getUserHealth() == null)
            return new ResponseEntity<>(new UserHealthDTO(), HttpStatus.OK);
        UserHealth userHealth = userHealthService.findById(user.getUserHealth().getId());
        return new ResponseEntity<>(UserHealthMapper.toDTO(userHealth), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<Long> addHealth(@Valid @RequestBody UserHealthDTO userHealthDTO, Principal principal) throws ResourceNotFoundException, UserNotFoundByUsernameException {
        User user = userService.findOneByUsername(principal.getName());
        QuestionnairePA questionnairePA = QuestionnaireMapper.toQuestionnaire(userHealthDTO.getQuestionnaire());
        UserHealth userHealth = userHealthService.addHealth(UserHealthMapper.toHealth(userHealthDTO), principal.getName());
        userPlannerService.createPlanner(user, userHealth, questionnairePA);
        return new ResponseEntity<>(userHealth.getId(), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<Long> updateHealth(@Valid @RequestBody UserHealthDTO userHealthDTO, Principal principal) throws ResourceNotFoundException, UserNotFoundByUsernameException {
        User user = userService.findOneByUsername(principal.getName());
        UserHealth userHealth = userHealthService.updateHealth(UserHealthMapper.toHealth(userHealthDTO));
        userPlannerService.updatePlanner(user, userHealth);
        return new ResponseEntity<>(userHealth.getId(), HttpStatus.OK);
    }

    @PutMapping(path = "/time/{minutes}")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity addTime(Principal principal, @PathVariable int minutes) throws ResourceNotFoundException, ResourceCantUpdateException, UserNotFoundByUsernameException {
        User user = userService.findOneByUsername(principal.getName());
        if ( user.getUserHealth() == null && user.getUserPlanner() == null)
            throw new ResourceNotFoundException("Planner");
        userHealthService.addTime(user.getUserHealth().getId(), minutes);
        return ResponseEntity.ok().build();
    }
}
