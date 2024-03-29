package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답 (data)
@RestController
public class HttpController {

	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청:"+m.getId()+m.getUsername();	
	}
	
	@PostMapping("/http/post")
	public String postTest(Member m) {
		return "post요청:"+m.getId()+m.getUsername();	
	}
	
	@PutMapping("/http/put")
	public String putTest() {
		return "put요청";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}
}
