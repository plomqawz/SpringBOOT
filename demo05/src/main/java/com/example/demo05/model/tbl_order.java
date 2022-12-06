package com.example.demo05.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class tbl_order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderid;
	private String note;
	private String ordername;
	private int price;
	
	@JoinColumn(name="user_id")
	@ManyToOne(fetch = FetchType.LAZY) // 조인이 실행시점에 되도록 함. 서버시작 시 오류가능.
	private tbl_user user;
}
