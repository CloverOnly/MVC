package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//Configuration:  스프링 시큐리티 설정하기 위해 사용
//EnavleWebSercurity: 모든 요청 URL 이 스프링 시큐리티의 제어를 받도록 함
//Bean: SecurityFilterChain 빈을 생성하여 설정할 수 있음

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	//인증되지 않은 모든 페이지의 요청을 허락한다는 의미이다. 따라서 로그인하지 않더라도 모든 페이지에 접근할 수 있도록 한다.
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((authorizeHttpRequests)->
			authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()).csrf((csrf)-> 
			csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
			.headers((headers)-> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter
					.XFrameOptionsMode.SAMEORIGIN)))
			.formLogin((formLogin)-> formLogin.loginPage("/user/login").defaultSuccessUrl("/"))
			.logout((logout)-> logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true));
		
	return http.build();

	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManger(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
}

/*
 
 스프링 시큐리티가 CSRF 처리 시 H2 콘솔 예외 처리할 수 있도록 파일 수정
 .csrf((csrf)-> 
			csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
 
 
 H2콘솔 오류 수정
 .headers((headers)-> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter
					.XFrameOptionsMode.SAMEORIGIN))) 
  
 
스프링 시큐리티에 로그인을 하기 위한 URL설정
.formLogin((formLogin)-> formLogin.loginPage("/user/login").defaultSuccessUrl("/"))


User 리포지터리와 서비스 생성(PasswordEncoder 빈을 만드는 가장 쉬운 방법
@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
}	

UserSecurity Service 와 PasswordEncoder 를 내부적으로 사용하여 인증과 권한 부여 프로세스를 처리
@Bean
	AuthenticationManager authenticationManger(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
로그인	
.formLogin((formLogin)-> formLogin.loginPage("/user/login").defaultSuccessUrl("/"))
	
로그아웃
.logout((logout)-> logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true));	
			
@PreAuthorize("isAuthenticated())
적용된 메서드가 로그아웃 상태에서 호출되면 로그인 페이지로 강제 이동됨.
 
@EnableMethodSecurity(prePostEnabled = true)
QuestionController 와 AnswerController에서 로그인 여부를 판별할 때 사용한 @PreAuthorize 애너테이션을 사용하기 위해 반드시 필요한 설정 
*/