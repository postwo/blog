package com.cos.blog.model;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
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
	
	@ManyToOne // many : board, user = one -> 한명의 유저는 여러개의 게시글을 쓸수 있다: 관계 
	@JoinColumn(name="userId") // 실제로 db에는 userid로 저장됨 이렇게만 들어가면 관계가 없다 그러므로 위에 @manytoone이런형식으로 작성 해야함
	private User user; //db는 오브젝트를 저장할 수 없기 때문에 FK를 사용한다 하지만 자바는 오브젝트를 저장할 수 있다
	//자바는 데이터베이스의 자료형을 맞춰서 테이블을 생성 = jpa=orm 을 사용하면 이방식처럼 사용가능 
	
	
	@CreationTimestamp //데이터가 insert update 될때 자동으로 값이 들어간다
	private Timestamp createDate;
	
	
	
	
}
