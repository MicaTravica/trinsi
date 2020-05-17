package com.app.trinsi.controller;

import com.app.trinsi.dto.ExerciseDTO;
import com.app.trinsi.dto.UserDTO;
import com.app.trinsi.dto.UserHealthDTO;
import com.app.trinsi.exceptions.UserNotFoundByUsernameException;
import com.app.trinsi.mapper.ExerciseMapper;
import com.app.trinsi.mapper.UserHealthMapper;
import com.app.trinsi.mapper.UserMapper;
import com.app.trinsi.model.Exercise;
import com.app.trinsi.model.User;
import com.app.trinsi.model.UserHealth;
import com.app.trinsi.service.UserHealthService;
import com.app.trinsi.service.UserPlannerService;
import com.app.trinsi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("trinsi/health")
public class UserHealthController {

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
    public ResponseEntity<UserHealthDTO> getMyData(Principal principal) throws UserNotFoundByUsernameException {
        User user = userService.findOneByUsername(principal.getName());
        if (user.getUserHealth() == null)
            return new ResponseEntity<>(new UserHealthDTO(), HttpStatus.OK);
        UserHealth userHealth = userHealthService.findByUsername(user.getId());
        return new ResponseEntity<>(UserHealthMapper.toDTO(userHealth), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addHealth(@RequestBody UserHealthDTO userHealthDTO) {
        UserHealth userHealth = userHealthService.addHealth(UserHealthMapper.toHealth(userHealthDTO));
        userPlannerService.getUserPlanner(userHealth);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
