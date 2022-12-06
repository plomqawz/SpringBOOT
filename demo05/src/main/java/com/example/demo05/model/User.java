package com.example.demo05.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
	private String email;
	private String name;
	private Date createdDate;
	@OneToOne(mappedBy="owner") // (mappedBy="owner") : 바라만볼 수 있게(참조만 가능)
	private MemberShipCard card;
}
