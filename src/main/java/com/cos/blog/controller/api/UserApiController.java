package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.controller.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userservice;

	@PostMapping("/auth/joinProc") //ajax url 경로
	public ResponseDto<Integer> save(@RequestBody User user) { //회원가입 로직이 실행되는 부분
		System.out.println("userapicontroller호출됨 : save 호출됨");
		user.setRole(RoleType.USER); // 이것만 생성해서 넣어준다, 나머지는 알아서 들어가기 때문에
		userservice.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	
	
	
	
	
//	@PostMapping("api/user/login") // 이거는 전동적인 방식이다
//	public ResponseDto<Integer> login(@RequestBody User user,HttpSession session){ // 로그인이 실행되는 부분
//		System.out.println("userapicontroller호출됨 : login호출됨");
//		User principal = userservice.로그인(user); //principal(접근주체)
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//	}
}

