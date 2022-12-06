package com.example.demo05.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo05.model.JpaMember;
import com.example.demo05.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	// 오토와이어드 대신 파이널로 선언하면 파이널로 지정된 생성자로 자동주입됨. 오토와이어드 역할 대신함.
	
	//추가
	public void save(JpaMember member) {
		memberRepository.save(member); // save : 이미 만들어져있음. insert 함수.
	}
	
	//전체보기
	public List<JpaMember> list(){
		return memberRepository.findAll(); // findAll : 이미 만들어져있음. 전체보기 함수.
	}
	
	//상세보기 (sql문 : select * from jpa_member where id(기본키)=3) // * Optional : 자바자체적 찾는 값 없으면 리턴은 널, 널포인트익셉션 방지
	public JpaMember detail(Long id) {
		return memberRepository.findById(id).get(); // get() 강제적으로 가져옴
	}
	
	//삭제
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}
	
	//수정 (더티체킹)
	@Transactional // 내부적으로 flush
	public void update(JpaMember member) {
		JpaMember m = memberRepository.findById(member.getId()).get(); // 1차 캐시에 들어있는 값
		m.setAddr(member.getAddr());
		m.setEmail(member.getEmail());
		m.setMemo(member.getMemo());
		m.setName(member.getName());
		m.setPassword(member.getPassword());
	}
	
}
