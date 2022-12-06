package com.example.demo02.service;

import java.util.List;

import com.example.demo02.dto.BoardDTO;

public interface BoardService {
	// 게시글 추가
	public void insert(BoardDTO board);
	
	// 게시글 전체보기
	public List<BoardDTO> list();
	
	// 게시글 상세보기
	public BoardDTO findByNum(int num);
	
	// 게시글 수정
	public void update(BoardDTO board);
	
	// 게시글 삭제
	public void delete(int num);
	
	// 게시글 개수
	public int getCount();
}
