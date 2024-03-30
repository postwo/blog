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
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest:";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//bulider  = 오버로딩을 생성해서 할 필요 없다 ,그리고 순서를 지켜서 값을 넣을 필요도 없다
		Member m = Member.builder().username("ssar").password("1234").email("ssar@naver.com").build();
		System.out.println(m.getId());
		m.setId(5000);
		System.out.println(m.getId());
			return "lombok test 완료";
	}
	
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
