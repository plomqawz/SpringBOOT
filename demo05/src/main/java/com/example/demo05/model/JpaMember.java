package com.example.demo05.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

// 객체가 만들어짐과 동시에 테이블 생성함. 엔티티 매니저가 관리함. 객체와 테이블 연관시킴. ORM(Object Relation Mapping) : 자바의 객체와 관계형 데이터베이스와 매핑.(객체 지향적)
// ORM 과 달리 마이바티스는 단순히 sql 커넥터
// 대문자가 언더바가 된다? 등 고려해서 변수설정하기.
// @Id 기본키설정.
// mySQL 사용이라 아이덴티티사용.(AI)
@Data
@Entity 
public class JpaMember {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String password;
	private String email;
	private String memo;
	@Column(name = "address")
	private String addr;
}
