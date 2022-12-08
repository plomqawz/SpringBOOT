package com.example.demo04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo04.dto.BoardDTO;
import com.example.demo04.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper bmapper;
	
	// 게시글 추가
	public void insert(BoardDTO board) {
		bmapper.insert(board);
	}
	
	// 게시글 전체보기
	public List<BoardDTO> list(){
		return bmapper.list();
		
	}
	
	// 게시글 상세보기
	public BoardDTO findByNum(int num) {
		return bmapper.findByNum(num);
		
	}
	
	// 게시글 수정
	public void update(BoardDTO board) {
		
	}
	
	// 게시글 삭제
	public void delete(int num) {
		
	}
	
	// 게시글 개수
	public int getCount() {
		return 0;
		
	}
}
