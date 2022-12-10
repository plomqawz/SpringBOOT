package com.example.demo06.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo06.model.User;

import lombok.Getter;

@Getter
public class PrincipalUser implements UserDetails{
	// 이번엔 user 대신 userdetails 구현.
	// 인터페이스라서 추가적 변수넣어서 구현가능.(이메일까지 고려한 인증객체 등)
	
	private User user;
	
	public PrincipalUser(User user) {
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>(); // 권한이 여러개일 수도 있기 때문.
		collect.add(()->{
			return user.getRole();
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
