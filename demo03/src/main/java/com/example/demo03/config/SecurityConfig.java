package com.example.demo03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // 기존 security.xml 대신한다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean // 빈 객체라는 것을 알림.
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests() // 권한을 요구
			.antMatchers("/user/*").authenticated() // 유저로 넘어오면 권한을 주고
			.anyRequest() // 그 외는
			.permitAll() // 모두 허용한다.
		.and()
			.formLogin() // 로그인 폼은
			.loginPage("/login") //login.jsp
			.loginProcessingUrl("/loginPro") // 폼액션이름(설정안할거면 디폴트 login 으로 하면됨)
			.defaultSuccessUrl("/")
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true);
	}
}
