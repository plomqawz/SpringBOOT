package com.example.demo04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo04.domain.CustomUser;
import com.example.demo04.dto.MemberDTO;
import com.example.demo04.mapper.MemberMapper;

@Service
public class MemberService implements UserDetailsService{

	@Autowired
	private MemberMapper mMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername 함수 호출함");
		MemberDTO member = mMapper.read(username);
		System.out.println("시큐 적용 안된 지금 member : " + member);
		return member==null? null:new CustomUser(member);
	}

	
}
