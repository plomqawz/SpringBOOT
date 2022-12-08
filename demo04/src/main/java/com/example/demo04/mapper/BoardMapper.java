package com.example.demo04.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo04.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	// 추가
	@Insert("insert into Board(title, writer, content) values(#{title},#{writer},#{content})")
	public void insert(BoardDTO board);
	
	// 전체보기
	@Select("select * from Board")
	public List<BoardDTO> list();
	
	// 상세보기
	@Select("select * from Board where num=#{num}")
	public BoardDTO findByNum(int num);
	
	// 개수
	
	// 댓글 개수
	
	// 수정
}
