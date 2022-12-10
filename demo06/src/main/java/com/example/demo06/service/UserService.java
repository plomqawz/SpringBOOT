package com.example.demo06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo06.model.User;
import com.example.demo06.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired // 암호화 객체의 주입. 유형같으면 알아서.
	private BCryptPasswordEncoder encoder;
	@Autowired
	private UserRepository userRepository;
	
	//회원가입
	public void register(User user) {
		//비번 암호화 시키고 추가
		String rawPassword=user.getPassword();
		String encPassword=encoder.encode(rawPassword);
		user.setPassword(encPassword); //암호화 된 비번.
		user.setRole("ROLE_USER");
		userRepository.save(user);
	}
	
	public User findById(Long num) {
		return userRepository.findById(num).get();
	}
	
}
