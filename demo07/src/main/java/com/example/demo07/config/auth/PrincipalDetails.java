package com.example.demo07.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo07.model.User;
import com.example.demo07.repository.UserRepository;

@Service
public class PrincipalDetails implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
		User user=userRepository.findByUsername(username);
		if(user==null) return null;
		PrincipalUser puser=new PrincipalUser(user);
		System.out.println("puser : "+puser);
		return puser;
	}

}
