package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity //User 클래스가 필드를 읽어서 mysql에 테이블이 생성된다
public class User {

	@Id //프라이머리키
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 db의 넘버링 전략을 따라간다, IDENTITY = auto-increment(mysql) 이다 
	private int id; // auto-increment
	
	@Column(nullable = false,length = 30) // null이 될수 없다
	private String username;
	
	@Column(nullable = false,length = 100) //123456 => 해쉬(비밀번호 암호화)
	private String password; 

	@Column(nullable = false,length = 50)
	private String email;
	
	@ColumnDefault("'user'") // 기본값 //varchar 로 쓰기 때문에 싱글쿼터를 추가
	private String role; //Enum을 쓰는게 좋다 //어떤 회원이 회원가입을 했을때 admin,user,manager // 도메인 = 어떤 범위가 정해졌다는것이다  
	
	@CreationTimestamp //시간이 자동으로 입력이 된다
	private Timestamp createDate;
	
	
}
