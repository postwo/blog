package com.cos.blog.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto-increment
	private int id; 

	@Column(nullable = false , length =100)
	private String title;
	
	@Lob // 대용량 데이터이때 사용함
	private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨= 그래서 용량이 큼
	
	@ColumnDefault("0") // 초기 조회수값은 0이기 때문에 이렇게 선언
	private int count; //조회수
	
	
	//fetch = FetchType.EAGER 니가 보드테이블을 셀렉트하면 user정보를 가지고 온다 왜냐 suer는 한건밖의 없기 때문에 
	//정보를 다보여줘야할때 EAGER전략 아니면 lazy전량이다
	@ManyToOne(fetch = FetchType.EAGER) // many : board, user = one -> 한명의 유저는 여러개의 게시글을 쓸수 있다: 관계 
	@JoinColumn(name="userId") // 실제로 db에는 userid로 저장됨 이렇게만 들어가면 관계가 없다 그러므로 위에 @manytoone이런형식으로 작성 해야함
	private User user; //db는 오브젝트를 저장할 수 없기 때문에 FK를 사용한다 하지만 자바는 오브젝트를 저장할 수 있다
	//자바는 데이터베이스의 자료형을 맞춰서 테이블을 생성 = jpa=orm 을 사용하면 이방식처럼 사용가능 
	//User 클래스의 프라이머리키값을 가지고 와서 사용한다는 뜻이다 
	
	
	//board를 셀렉트할때 조인문을 통해서 값을 얻기 위해서 필요한거다
	//fetch = FetchType.EAGER 이걸 사용하지 않은이유는 reply이 엄청많기 때문에 이전략은 안쓴다,그러므로 기본전략이 lazy전략이다  
	@OneToMany(mappedBy = "board",fetch=FetchType.EAGER) // 한명의 게시글에는 여러명의 답글이 있다   ,mappedBy = "board"이거는 reply에있는 필드이다
	//mappedby가 적혀있으면 연간관계의 주인이 아니다 (난 fk가 아니에요) ,db에 컬럼을 만들지 마세요 
	//Reply 테이블에 있는 board가 fk라는거다 
	private List<Reply> reply; // 하나의 게시글에는 여러명이 답변을 달수 있다 // 한명이 아니고 여러개의 답글이기 때문에 list로 만든다
	// joincolum을 달 필요가 없다 : 여러개의 답글들이 많기 때문에 데이터베이스는 하나의 값을 가지기 때문에 fk가 필요없다 1,2이렇게 쓰이면 안되기 때문에
	
	@CreationTimestamp //데이터가 insert update 될때 자동으로 값이 들어간다
	private Timestamp createDate;
	
	
	
	
}
