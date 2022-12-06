package com.example.demo02.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo02.dto.CommentDTO;

@Mapper
public interface CommentMapper {
	// 추가
	@Insert("insert into commentboard(userid, content, regdate, bnum) "
			+ "values(#{userid}, #{content}, now(), #{bnum})")
	public void insert(CommentDTO comment);
	// 전체보기
	@Select("select * from commentboard where bnum=#{bnum}")
	public List<CommentDTO> getList(int num);
	// 삭제
	@Delete("delete from commentboard where cnum=#{cnum}")
	public void delete(int num);
	// 상세보기 (cnum 받아와서 bnum 찾기 위함)
	@Select("select * from commentboard where cnum=#{cnum}")
	public CommentDTO findByNum(int cnum);
}
