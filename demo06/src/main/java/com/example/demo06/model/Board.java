package com.example.demo06.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="tbl_board06")
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long num;
	private String title;
	private String content;
	
	// mySQL 에서 regdate 컬럼 생성 시 기본값 now() 준것처럼 세팅.
	@CreationTimestamp // insert 시 시간 자동저장.
	@Temporal(TemporalType.TIMESTAMP) // 날짜타입 매핑.
	@Column(name = "regdate") // 테이블컬럼명 변경시.
	private Date regdate;
	private Long hitcount;
	private Long replycnt;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE) // commentboard 의 board 바라만 보게끔. // 보드 지워지면 코멘트들도 지워지게끔 캐스캐이드.
	// ...many : 조인전략이 실행시점.
	@JsonIgnoreProperties("board") // 없으면 무한참조때문에 오류뜬다.
	private List<Comment> comments;
	
	@ManyToOne(fetch=FetchType.LAZY) // 조인전략을 실행시점으로 바꿔봄. > 게시글의 댓글안뜸.
	//@ManyToOne(fetch=FetchType.EAGER) // 디폴트 조인전략. > 뜬다.
	// ...one : 디폴트 조인전략이 처음부터.
	@JoinColumn(name = "user_id")
	@JsonIgnore ///
	private User user;
	
	@PrePersist//초기값설정
	public void prePersist() {
	this.hitcount = this.hitcount==null? 0:this.hitcount;
	this.replycnt = this.replycnt==null? 0:this.replycnt;
	}
	
}
