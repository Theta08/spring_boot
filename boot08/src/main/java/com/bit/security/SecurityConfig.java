package com.bit.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//db연동
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BitUserService userService;
	
	@Override
		protected void configure(HttpSecurity http) throws Exception {
			log.info("security config........");
			//guest 시작되는 URI 모든 허죵
			http.authorizeRequests().antMatchers("/guest/**").permitAll();
			http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
			http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
			//로그인 폼 :springboot에서 제공되는 로그인폼
			//http.formLogin();
			http.formLogin().loginPage("/login");//login.html 호출
			//권한이 없는 페이지 호출 : manager 로그인후 -> localhost/admin 호출
			http.exceptionHandling().accessDeniedPage("/accessDenied"); 
			//세션 전체 제거
			http.logout().logoutUrl("/logout").invalidateHttpSession(true);
			http.userDetailsService(userService);
		}
	
	//로그인폼에서 입력한 계정 확인
	@Autowired
	public void configgureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		log.info("build Auth global......");
		
		String query1 = "SELECT uid username, upw password, true enabled "
				+ "FROM tbl_members WHERE uid= ?";
				
		String query2 = "SELECT member uid, role_name role "
				+ "FROM tbl_member_role WHERE member = ?";
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(query1)
		.authoritiesByUsernameQuery(query2); 
		
//		log.info("build Auth global......");
//		auth.inMemoryAuthentication()
//		.withUser("manager")
//		.password("1234")
//		.roles("MANAGER");
	}
	
	//비밀번호 암호화 인터페이스
	@Bean	//객체를 등록하는 어노테이션
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return rawPassword.equals(encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			} 
		};
	}
	
}  
