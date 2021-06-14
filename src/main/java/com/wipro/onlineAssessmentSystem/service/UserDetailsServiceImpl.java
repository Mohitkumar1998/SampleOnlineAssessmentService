package com.wipro.onlineAssessmentSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wipro.onlineAssessmentSystem.entity.user;
import com.wipro.onlineAssessmentSystem.repository.userRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private userRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		user thisuser;
		if(userRepo.findById(email).isPresent()) {
			thisuser=userRepo.findById(email).get();
		}else {
			throw new UsernameNotFoundException(email, null);
		}
		return new myUserDetails(thisuser);
	}

}
