package com.example.demo06.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo06.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	//댓글추가
	@Modifying
	@Query(value="insert into tbl_comment06(content, regdate, bnum, user_id) values (?1, now(), ?2, ?3)",
			nativeQuery = true) // sql 문처럼 쓸 수 있도록 함.
	public void insert(String content, Long bnum, Long user_id);
	
	//댓글리스트
	//JPQL(Java Persistence Query Language : 엔티티 객체를 중심으로 단순하게 쿼리사용가능.)
	//즉 테이블이름(tbl_comment06) 말고 엔터티이름(Comment)으로 써야 함.
	//@Query("select sc from Comment sc where bnum=?1") // ?1 : 첫번째 매개변수(bnum) // EAGER
	@Query("select sc from Comment sc join fetch sc.board where bnum=?1") // LAZY(패치조인 사용)
	public List<Comment> findByBnum(Long bnum);
}
