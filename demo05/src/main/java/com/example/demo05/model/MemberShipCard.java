package com.example.demo05.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class MemberShipCard {
	@Id
	private String cardNumber;
	@JoinColumn(name="user_email") // @JoinColumn : 외래키 설정.
	@OneToOne
	private User owner;
	private Date expiryDate;
	private boolean enabled;
}
