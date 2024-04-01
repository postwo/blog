package com.cos.blog.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

//DAO 하고 같다
//자동으로 bean으로 된다
//@Repository 생략으로 가능하다
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	
	
	
	
	//JPA Naming 쿼리
	//select * from user where useraname=? AND password=?;
	//첫번째방법
	//User findByUsernameAndPassword(String username,String password); //이걸로 로그인 x
	
	//두번째 방법
//	@Query(value="select * from user where useraname=? AND password=?",nativeQuery=true)
//	User login(String username , String password);
}
