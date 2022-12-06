package com.example.demo05.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hotel_review") // 테이블 이름 바꿀 때.(디폴트 엔티티네임=테이블 네임)
public class Review {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int rate;
	private String comment;
	private Date ratingDate;
	
	@JoinColumn(name = "hotel_id")
	@ManyToOne // 다대일 관계.
	private Hotel hotel;
}
