package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 exception이 발생하면 이클래스로 들어오게 하기위해 사용
@RestController
public class GlobalExcptionHandler {

	//IllegalArgumentException이 발생하면 그 exception에 대한 에러를 e매개변수로 전달 후 밑의 메서드 리턴 
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"<h1>";
	}
	
	
}
