package com.example.demo03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo03.domain.CustomUser;
import com.example.demo03.dto.MemberDTO;
import com.example.demo03.mapper.MemberMapper;

@Service
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberMapper mMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
		MemberDTO member = mMapper.read(username); // 시큐리티 적용x
		System.out.println("member : "+member); 
		return member==null? null:new CustomUser(member); // 멤버가 널이면 시큐리티 적용된 커스텀유저 부여.
	}

}
