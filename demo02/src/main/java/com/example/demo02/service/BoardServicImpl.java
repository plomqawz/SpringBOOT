package com.example.demo02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo02.dao.BoardMapper;
import com.example.demo02.dto.BoardDTO;

@Service
public class BoardServicImpl implements BoardService{
	
	@Autowired
	private BoardMapper bmapper;

	@Override // 게시글 추가
	public void insert(BoardDTO board) {
		bmapper.insert(board);
	}

	@Override // 게시글 전체보기
	public List<BoardDTO> list() {
		return bmapper.list();
	}

	@Override // 게시글 상세보기
	public BoardDTO findByNum(int num) {
		return bmapper.findByNum(num);
	}
	
	@Override // 게시글 수정
	public void update(BoardDTO board) {
		bmapper.update(board);
	}

	@Override // 게시글 삭제
	public void delete(int num) {
		bmapper.delete(num);
	}

	@Override // 게시글 개수
	public int getCount() {
		return bmapper.getCount();
	}

}
