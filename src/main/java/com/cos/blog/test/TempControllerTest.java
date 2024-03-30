package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("htemp home");
		//파일리턴 기본경로:src/main/resources/static
		//리턴명을 /home.html이라고 해야한다
		//풀경로: src/main/resources/static/home.html
		return "/home.html";
	}
}
