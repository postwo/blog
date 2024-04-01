package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는것
@Configuration // 빈등록(IOC 관리)
@EnableWebSecurity //시큐리티 필터가 등록이 된다
@EnableGlobalMethodSecurity(prePostEnabled=true)//특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()//요청이 들어오면
			.antMatchers("/auth/**") // 여기경로로 들어오는건 누구나 가능하다
			.permitAll()// 그게 아닌 모든 주소는 
			.anyRequest() //위에 요청이 아닌 
			.authenticated() // 경우는 다 인증을 해야해
			.and()
			.formLogin()
			.loginPage("/auth/loginForm"); // 인증이 필요한 곳에 접속하면 login폼으로 이동해라
	}
}
