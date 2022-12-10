package com.example.demo06.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "tbl_comment06")
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cnum;
	private String content;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "regdate")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date regdate;
	
	//bnum(보드테이블과 조인하는 컬럼)
	@ManyToOne
	@JoinColumn(name = "bnum") // board 테이블의 num 인데 이름 bnum 하면 컬럼명도 bnum 으로 생성.
	private Board board;
	
	//userid(유저와 관계있는 컬럼)
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
