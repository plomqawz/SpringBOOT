package com.example.demo02.service;

import java.util.List;

import com.example.demo02.dto.CommentDTO;

public interface CommentService {
	// 추가
	public void insert(CommentDTO comment);
	// 전체보기
	public List<CommentDTO> getList(int num);
	// 삭제
	public void delete(int num);
}
