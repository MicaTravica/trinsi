package com.app.trinsi.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.trinsi.dto.LoginDTO;
import com.app.trinsi.dto.PasswordChangeDTO;
import com.app.trinsi.dto.UserDTO;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.exceptions.UserNotFoundByUsernameException;
import com.app.trinsi.mapper.UserMapper;
import com.app.trinsi.model.User;
import com.app.trinsi.security.TokenUtils;
import com.app.trinsi.service.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("trinsi")
public class UserController extends BaseController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	ApplicationEventPublisher eventPubisher;
	
	@PostMapping(value="/login",
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) throws UsernameNotFoundException {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
				loginDTO.getPassword());
		authenticationManager.authenticate(token);
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
		return new ResponseEntity<>(tokenUtils.generateToken(userDetails), HttpStatus.OK);
	}
	
	@PostMapping(value="/registration", 
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>registration(@RequestBody UserDTO userDTO) throws Exception {
		userService.registration(UserMapper.toUser(userDTO));
		return new ResponseEntity<>("You are registered, now you need to verify your email", HttpStatus.OK);
	}
	
	@GetMapping(value = "/userme", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getMyData(Principal user) throws UserNotFoundByUsernameException {
		return new ResponseEntity<>(UserMapper.toDTO(userService.findOneByUsername(user.getName())), HttpStatus.OK);
	}


	@PutMapping(value = "/user", 
				consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody UserDTO userDto) throws Exception {
		User changedUser = userService.update(UserMapper.toUser(userDto));
		Collection<SimpleGrantedAuthority> nowAuthorities =
				(Collection<SimpleGrantedAuthority>)SecurityContextHolder
							.getContext().getAuthentication().getAuthorities();
		UsernamePasswordAuthenticationToken authentication =
				new UsernamePasswordAuthenticationToken(changedUser.getUsername(), changedUser.getPassword(), nowAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(changedUser.getUsername());
		return new ResponseEntity<>(tokenUtils.generateToken(userDetails), HttpStatus.OK);
	}
	
	@PutMapping(value= "/user/password", 
				consumes= MediaType.APPLICATION_JSON_VALUE, 
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> changePassword(@RequestBody PasswordChangeDTO pcDto) throws Exception
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		userService.changeUserPassword(pcDto, username);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return new ResponseEntity<>(tokenUtils.generateToken(userDetails), HttpStatus.OK);
	}
	
	@GetMapping(value= "/user/verify/{token}",  
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> verifiedEmail(@PathVariable("token") String token) throws ResourceNotFoundException
	{
		userService.verifiedUserEmail(token);
	    return new ResponseEntity<>("Email verified", HttpStatus.OK);
	}
}
