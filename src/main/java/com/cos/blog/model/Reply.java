package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply { //답변 테이블

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto-increment
	private int id; 
	
	@Column(nullable = false, length = 200) 
	private String content;
	
	@ManyToOne // 하나의 게시글에는 여러개의 답변
	@JoinColumn(name="boardId") // 컬럼이름 지정
	private Board board; //게시글 아이디 
	
	
	@ManyToOne   //하나의 유저는 여러개의 답글을 달수있다
	@JoinColumn(name="userId")
	private User user; //이답변을 누가 적었는지 
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
