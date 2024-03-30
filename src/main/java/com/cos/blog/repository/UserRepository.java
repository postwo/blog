package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

//DAO 하고 같다
//자동으로 bean으로 된다
//@Repository 생략으로 가능하다
public interface UserRepository extends JpaRepository<User, Integer> {

}
