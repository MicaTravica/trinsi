package com.app.trinsi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.trinsi.model.User;
import com.app.trinsi.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepositoy;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepositoy.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with username '%s'", username)));
		if(!user.isVerified()) {
			throw new UsernameNotFoundException("You need to verify your email.");
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserRole().toString()));
		return new org.springframework.security.core.userdetails.User(
	    		user.getUsername(),
	    		user.getPassword(),
	    		grantedAuthorities);
	}

}
