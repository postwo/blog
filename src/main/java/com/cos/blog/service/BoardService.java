package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.NonUniqueSessionRepositoryException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌 .ioc를 해준다
@Service
public class BoardService {

	@Autowired
	private BoardRepository  boardRepository;
	
	
	@Transactional 
	public void 글쓰기(Board board,User user) { //title,content
	board.setCount(0);
	board.setUser(user);
	 boardRepository.save(board);
	}

	
}



