package com.example.demo07.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo07.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username); // where절 username 맞춰서 쿼리문 생성. (네임드 쿼리) == 쿼리메서드(메서드 이름으로 쿼리가 생성됨.)

	public Page<User> findByUsernameContaining(String word,Pageable pageable);
	public Page<User> findByEmailContaining(String word,Pageable pageable);

	@Query(value="select count(*) from User where username like CONCAT('%',:word,'%')")
	public Long cntUnameSearch(@Param("word") String word);
	@Query(value="select count(*) from tbl_user07 where email like CONCAT('%',:word,'%')",
			nativeQuery = true)
	public Long cntEmailSearch(@Param("word") String word);
}
