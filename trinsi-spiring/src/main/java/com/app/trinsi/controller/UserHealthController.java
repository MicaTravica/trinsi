package com.app.trinsi.controller;

import com.app.trinsi.dto.UserHealthDTO;
import com.app.trinsi.exceptions.ResourceCantUpdateException;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.exceptions.UserNotFoundByUsernameException;
import com.app.trinsi.mapper.UserHealthMapper;
import com.app.trinsi.model.User;
import com.app.trinsi.model.UserHealth;
import com.app.trinsi.service.UserHealthService;
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
public class UserHealthController extends BaseController {

    private final UserHealthService userHealthService;
    private final UserService userService;

    @Autowired
    public UserHealthController(UserHealthService userHealthService, UserService userService){
        this.userHealthService  = userHealthService;
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserHealthDTO> getUserHealth(Principal principal) throws UserNotFoundByUsernameException {
        User user = userService.findOneByUsername(principal.getName());
        if (user.getUserHealth() == null)
            return new ResponseEntity<>(new UserHealthDTO(), HttpStatus.OK);
        UserHealth userHealth = userHealthService.findById(user.getUserHealth().getId());
        return new ResponseEntity<>(UserHealthMapper.toDTO(userHealth), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addHealth(@RequestBody UserHealthDTO userHealthDTO, Principal principal) throws ResourceNotFoundException {
        UserHealth userHealth = userHealthService.addHealth(UserHealthMapper.toHealth(userHealthDTO), principal.getName());
        return new ResponseEntity<>(userHealth.getId(), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> updateHealth(@RequestBody UserHealthDTO userHealthDTO) throws ResourceNotFoundException {
        UserHealth userHealth = userHealthService.updateHealth(UserHealthMapper.toHealth(userHealthDTO));
        return new ResponseEntity<>(userHealth.getId(), HttpStatus.OK);
    }

    @PutMapping(path = "/time/{minutes}")
    public ResponseEntity addTime(Principal principal, @PathVariable int minutes) throws ResourceNotFoundException, ResourceCantUpdateException, UserNotFoundByUsernameException {
        User user = userService.findOneByUsername(principal.getName());
        if ( user.getUserHealth() == null && user.getUserPlanner() == null)
            throw new ResourceNotFoundException("Planner");
        userHealthService.addTime(user.getUserHealth().getId(), minutes);
        return ResponseEntity.ok().build();
    }
}
