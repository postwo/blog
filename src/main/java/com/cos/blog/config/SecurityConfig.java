package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;


//빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는것
@Configuration // 빈등록(IOC 관리)
@EnableWebSecurity //시큐리티 필터가 등록이 된다
@EnableGlobalMethodSecurity(prePostEnabled=true)//특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired // Di를 해준다
	private PrincipalDetailService pricipaldetailservice;
	
	
	@Bean //IOC가 된다 ( new BCryptPasswordEncoder(); 이거를 스프링이 관리한다)
	public BCryptPasswordEncoder encodePWD() { // 암호화
		//String encPassword = new BCryptPasswordEncoder().encode("1234"); test 용
		return new BCryptPasswordEncoder(); 
	}
	
	//시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	//해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 db에 있는 해쉬랑 비교할 수 있음
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(pricipaldetailservice).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf 토큰 비활성화 (테스트시 걸어두는게 좋음) 이걸안걸어두면 계속 오류 뜸
			.authorizeRequests()//요청이 들어오면
			.antMatchers("/","/auth/**","/js/**","/css/**","/image/**") // 여기경로로 들어오는건 누구나 가능하다
			.permitAll()// 그게 아닌 모든 주소는 
			.anyRequest() //위에 요청이 아닌 
			.authenticated() // 경우는 다 인증을 해야해
			.and()
			.formLogin()
			.loginPage("/auth/loginForm") // 인증이 필요한 곳에 접속하면 login폼으로 이동해라
			.loginProcessingUrl("/auth/loginProc") // 로그인 컨트롤러를 안만들고 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다 
			.defaultSuccessUrl("/"); // 로그인성공에는 "/"여기로 이동
			//.failureUrl("/fail") 로그인 실패시 이동경로
	}
}
