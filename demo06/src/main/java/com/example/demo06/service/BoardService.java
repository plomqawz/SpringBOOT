package com.example.demo06.service;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo06.model.Board;
import com.example.demo06.model.User;
import com.example.demo06.repository.BoardRepository;

@Transactional(readOnly = true) // flush 자동호출x 수동으로 한다는 의미. @Transactional 붙인 메서드만 flush 이루어짐.
//flush 가 이루어져야 DB 에 값 변경됨.
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional // 적어야 flush 이루어짐.
	public void insert(Board board, User user) { // userid 만 받아오는게 아니라 user 형의 user를 매개변수로 받아 인서트할때 넣는다.
		board.setUser(user);
		boardRepository.save(board);
	}
	
	// 페이징 없이 전체보기
	public List<Board> findAll() {
		return boardRepository.findAll();
	}
	
	// 페이징 포함 전체보기
	public Page<Board> findAll(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	// 페이징과 검색 포함 전체보기
	public Page<Board> findAll(String field, String word, Pageable pageable){
		Page<Board> lists = boardRepository.findAll(pageable);
		if(field.equals("title")) {
			lists = boardRepository.findByTitleContaining(word,pageable); // Containing : like 사용.
		}else if(field.equals("content")) {
			lists = boardRepository.findByContentContaining(word,pageable);
		}
		return lists;
	}
	
	// 게시글 개수
	public Long count() {
		return boardRepository.count();
	}
	
	// 검색한 게시글 개수
	public Long count(String field, String word) {
		Long count = boardRepository.count();
		if(field.equals("title")) {
			count = boardRepository.cntTitleSearch(word);
		}else if(field.equals("content")) {
			count = boardRepository.cntContentSearch(word);
		}
		return count;
	}
	
	@Transactional // 안적으면 DB반영 안이루어짐. (영속성 컨텍스트, 더티체킹)
	public Board findById(Long num) {
		Board board = boardRepository.findById(num).get();
		board.setHitcount(board.getHitcount()+1);
		return board;
	}
	
	@Transactional
	public void delete(Long num) {
		boardRepository.deleteById(num); // 이렇게 사용해도 되지만,
		//boardRepository.deleteByNum(num); // 이렇게 쓰려면 Num 이 기본키가 아니라서 레파지토리에서 쿼리문 작성해야함.
	}
	
	// 수정 ===> 더티체킹
	// 업데이트는 없음. 더티체킹으로 해야함. 영속성 컨텍스트 값을 들고와서 바꾸고 플러시하면 디비도 수정.
	@Transactional
	public void update(Board board) { 
		Board b = boardRepository.findById(board.getNum()).get();
		b.setTitle(board.getTitle());
		b.setContent(board.getContent());
		b.setRegdate(new Date()); // 날짜를 최종수정날짜로 변경.
	}
}
