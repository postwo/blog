package com.cos.blog.controller.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> { //응답할때 사용
	

	int status; // ok가 찍힌다
	T data;
	
	
	
}
