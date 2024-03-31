package com.cos.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.controller.dto.ResponseDto;
import com.cos.blog.model.User;

@RestController
public class UserApiController {

	@PostMapping("/api/user") //ajax url 경로
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("userapicontroller호출됨 : save 호출됨");
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
	//41부터 듣기
}

