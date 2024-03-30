package com.cos.blog.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired //의존성주임 (di) 
	private UserRepository userrepository; //

	@PostMapping("/dummy/join")
	public String join(User user) { //key=value(규칙)
		System.out.println("id:"+user.getId());
		System.out.println("username:"+user.getUsername());
		System.out.println("password:"+user.getPassword());
		System.out.println("email:"+user.getEmail());
		System.out.println("role:"+user.getRole());
		System.out.println("create:"+user.getCreateDate());
		
		user.setRole(RoleType.USER); 
		userrepository.save(user); //role이 안들어감
		return "회원가입이 완료되었습니다";
	}
}
