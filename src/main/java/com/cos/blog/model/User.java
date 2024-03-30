package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity //User 클래스가 필드를 읽어서 mysql에 테이블이 생성된다
//@DynamicInsert // null값들어가걸 방지하고 디폴트값을 들어가게 해준다, insert시에 null인 필드를 제외시켜준다
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
	
	
	//@ColumnDefault("'user'") //이게 들어갈려면 null로 들어가면 안되기 때문에  @DynamicInsert이걸 사용
	// 기본값 //varchar 로 쓰기 때문에 싱글쿼터를 추가
	//DB는 RoleType이라는게 없다 그러므로 밑에 어노테이션을 분여줘야한다
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다 //어떤 회원이 회원가입을 했을때 admin,user,manager // 도메인 = 어떤 범위가 정해졌다는것이다  
	//ADMIN,USER
	
	
	@CreationTimestamp //시간이 자동으로 입력이 된다
	private Timestamp createDate;
	
	
}
