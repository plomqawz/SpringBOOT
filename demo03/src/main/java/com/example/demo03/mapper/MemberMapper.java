package com.example.demo03.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo03.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	public MemberDTO read(String username);

}
