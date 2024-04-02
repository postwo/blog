package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	@GetMapping({"","/"})  //@AuthenticationPrincipal PrincipalDetail principal세션 접근
	public String index(@AuthenticationPrincipal PrincipalDetail principal) { //컨트롤러에서 세션을 어떻게 찾는지?
		// /WEB-INF/views/index.jsp
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
