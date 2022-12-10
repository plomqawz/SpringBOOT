package com.example.demo06.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo06.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username); // where절 username 맞춰서 쿼리문 생성. (네임드 쿼리) == 쿼리메서드(메서드 이름으로 쿼리가 생성됨.)
}
