package com.example.demo05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo05.model.JpaMember;

public interface MemberRepository 
         extends JpaRepository<JpaMember, Long>{ // <쓰고자 하는 entity, 기본키의 타입>

}
