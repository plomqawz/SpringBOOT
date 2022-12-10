package com.example.demo06.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo06.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	public void deleteByNum(Long num);

	public Page<Board> findByTitleContaining(String title, Pageable pageable);
	public Page<Board> findByContentContaining(String content, Pageable pageable);
	
	// 제목으로 검색한 개수
	@Query(value="select count(*) from Board where title like CONCAT('%',:word,'%')") // :==?
	public Long cntTitleSearch(@Param("word") String word);
	// 내용으로 검색한 개수
	@Query(value="select count(*) from tbl_board06 where content like CONCAT('%',:word,'%')",
			nativeQuery = true)
	public Long cntContentSearch(@Param("word") String word);
}
