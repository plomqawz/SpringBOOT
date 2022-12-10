package com.example.demo07.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo07.model.User;
import com.example.demo07.repository.UserRepository;

@Service
public class UserService {

	@Autowired // 암호화 객체의 주입. 유형같으면 알아서.
	private BCryptPasswordEncoder encoder;
	@Autowired
	private UserRepository userRepository;

	// 회원가입
	public void register(User user) {
		// 비번 암호화 시키고 추가
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword); // 암호화 된 비번.
		user.setRole("ROLE_USER");
		userRepository.save(user);
	}

	public User findById(Long num) {
		return userRepository.findById(num).get();
	}

	// 전체보기 (페이징+검색)
	public Page<User> findAll(String field, String word, Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);
		if (field.equals("username")) {
			users = userRepository.findByUsernameContaining(word, pageable);
		} else if (field.equals("email")) {
			users = userRepository.findByEmailContaining(word, pageable);
		}
		return users;

	}

	// 개수 (검색)
	public Long count(String field, String word) {
		Long count = userRepository.count();
		if (field.equals("username")) {
			count = userRepository.cntUnameSearch(word);
		} else if (field.equals("email")) {
			count = userRepository.cntEmailSearch(word);
		}
		return count;
	}

	// 회원정보수정(더티체킹)
	@Transactional
	public void userupdate(User user) {
		User u = userRepository.findById(user.getId()).get();
		u.setUsername(user.getUsername());
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		u.setPassword(encPassword); // 암호화 된 비번.
		u.setEmail(user.getEmail());
	}

	// 회원탈퇴
	public void userdelete(Long id) {
		userRepository.deleteById(id);
	}
}
