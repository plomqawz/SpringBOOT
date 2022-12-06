package com.example.demo05.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class tbl_user {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;
	private String address;
	private String nickname;
	private String username;
	
	@OneToMany(mappedBy = "user")
	private List<tbl_order> orders;
}
