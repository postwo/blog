package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 된면 UserDetails 타입의 오브젝트를
//스프링 시큐리티의 고유한 세션 저장송에 저장을 해준다
@Getter
public class PrincipalDetail implements UserDetails{

	private User user; //콤포지션 = 객체를 품고있는걸 뜻한다

	public PrincipalDetail (User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴한다 (true: 만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있는지 안잠겨있는지 (true: 안 잠겨있다)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호가 만료되지 않았는지 리턴한다 (true :만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정이 활성화 (사용가능)인 지 리턴한다 (true:활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	//계정의 권한을 리턴한다(어떤 권한을 가졌는지)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() { // 익명클래스 생성
//			
//			@Override
//			public String getAuthority() { //추상메서드가 오버라이딩 되면서
//				return "ROLE_"+user.getRole(); //ROLE_ 앞에 이런 형식으로 꼭넣어줘야 한다 
//			}
//		});

		//위에꺼를 람다식으로 만든거다		
		collectors.add(()->{return "ROLE_"+user.getRole();});
		
		return collectors;
	}
	
	
}
