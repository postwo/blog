package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.NonUniqueSessionRepositoryException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌 .ioc를 해준다
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired //di가 되서 주입
	private BCryptPasswordEncoder encoder;
	
	/* @Transactional
	 * 모든 작업들이 성공적으로 완료되어야 작업 묶음의 결과를 적용하고, 어떤 작업에서 오류가 발생했을 때는 이전에 있던 모든 작업들이
	 * 성공적이었더라도 없었던 일처럼 완전히 되돌리는 것이 트랜잭션의 개념이다. 데이터베이스를 다룰 때 트랜잭션을 적용하면 데이터 추가, 갱신,
	 * 삭제 등으로 이루어진 작업을 처리하던 중 오류가 발생했을 때 모든 작업들을 원상태로 되돌릴 수 있다. 모든 작업들이 성공해야만 최종적으로
	 * 데이터베이스에 반영하도록 한다.
	 */
	@Transactional // 이걸 선언하면 메서드가 하나의 트랜잭션으로 묶이게 된다
	public void 회원가입(User user) { //성공하면 commit 실패하면 rollback
		String rawPassword = user.getPassword(); // 원본 비밀번호 1234
		String encPassword = encoder.encode(rawPassword);//비밀번호 암호화 = 해쉬
		user.setPassword(encPassword);//해쉬암호화된거를 set시켜준다
		user.setRole(RoleType.USER); // 이것만 생성해서 넣어준다, 나머지는 알아서 들어가기 때문에
		userRepository.save(user);  //save가 안되면 handler가 처리한다
	}

	
	//이로그인 사용 x
//	@Transactional(readOnly = true) // select 할때 트랜잭션 시작 서비스 종료될때 트랜재션이 종료될건데 (정합성이 유지된다)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword()); 
//	}
}



