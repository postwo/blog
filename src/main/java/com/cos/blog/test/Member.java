package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data //이거는 게터 세터 전부다 가지고 있는거다
//@Getter
//@Setter
@NoArgsConstructor
//@RequiredArgsConstructor // final이 붙은 생성자를 생성해준다
public class Member {

	//데이터베이스에서 가지고 온 값을 변경할일이 없기 때문에 final을 붙인거다 
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	@Builder //오버로딩을 안하고 빌더를 쓰면
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	
	
	
	
	

	
	
}
