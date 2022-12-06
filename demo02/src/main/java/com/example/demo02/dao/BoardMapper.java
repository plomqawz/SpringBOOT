package com.example.demo02.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo02.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	// 추가
	@Insert("insert into Board(title, writer, content) values(#{title}, #{writer}, #{content})")
	public void insert(BoardDTO board);
	
	// 전체보기
	//@Select("select * from Board")
	public List<BoardDTO> list();
	
	// 상세보기
	@Select("select * from Board where num=#{num}")
	public BoardDTO findByNum(int num);
	
	// 개수 (xml)
	public int getCount();
	
	// 댓글 개수 : updateReplyCnt()
	@Update("update Board set replyCnt=replyCnt+#{amount} where num=#{bnum}")
	public void updateReplyCnt(@Param("bnum") int bnum, @Param("amount") int amount);
	
	// 수정
	@Update("update Board set title=#{title}, writer=#{writer}, content=#{content}, regdate=now() where num=#{num}")
	public void update(BoardDTO board);
	
	// 삭제
	@Delete("delete from Board where num=#{num}")
	public void delete(int num);
	
}
