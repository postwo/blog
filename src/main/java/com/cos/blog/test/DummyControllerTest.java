package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import lombok.Delegate;


//리턴하는게 html파일이 아니라 data를 리턴해주는 controller = restcontroller 


@RestController
public class DummyControllerTest {

	@Autowired //의존성주임 (di) 
	private UserRepository userrepository; 
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {		
		try {
			userrepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제 실패 .해당 id는 db에 없습니다";
		}
		
		userrepository.deleteById(id);
		return "삭제 되었습니다.id:"+id;
	}
	
	
	
	//save 함수는 id를 전달하지 않으면 insert 를 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
	//email,password
	@Transactional//메서드 종료(리턴)될때 자동 commit이 된다
	@PutMapping("/dummy/user/{id}")
	//@RequestBody json데이터를 받는다
	public User updateUser(@PathVariable int id,@RequestBody User requestUser ) {
	System.out.println("id :"+id);	
	System.out.println("password :"+requestUser.getPassword());
	System.out.println("email :"+requestUser.getEmail());
	
	User user = userrepository.findById(id).orElseThrow(()->{ //실패할수도 있기 때문에
	return new IllegalArgumentException("수정에 실패 하셨습니다");		
	});
	
	//이렇게 변경해야 save로 업데이트를 할수있다
	user.setPassword(requestUser.getPassword());//이게없으면 영속화만 된거고 이게있으면 값을수정한거다
	user.setEmail(requestUser.getEmail());//이게없으면 영속화만 된거고 이게있으면 값을수정한거다
	//requestUser.setId(id);
	//requestUser.setUsername("ssar");
	//userrepository.save(user);//update를 할때는 save거의 안쓴다 왜냐하면 다른값들은 null로 변하기 때문에
	
	
	return user;
	}
	
	
	@GetMapping("/dummy/users")
	public List<User> list(){ //한건이 아니라 여러건으로 받으거여서 list사용
		return userrepository.findAll(); //전체가 리턴
	}
	

	//한 페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pagelist(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC)Pageable pageable){ 
		//List<User> users=userrepository.findAll(pageable).getContent(); //getContent()이걸쓰면(대신list로바꿔야한다) totalpage등보기 싫으면 이렇게 사용 보고싶은면 content지우기
		Page<User> pagingusers=userrepository.findAll(pageable);
		
		List<User> users = pagingusers.getContent();
		return users;
		//return userrepository.findAll(pageable); 바로이렇게 보내도 됨
	}
	

	//{id} 주소로 파라메터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id ) { // user객체로 반환
		//optional 만약에 user/4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?\
		//그럼 return null이 리터이 되자나 그럼 프로그램에 문제가 있지 않겠니?
		//optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해
		User user = userrepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
			}
		});
//				userrepository.findById(id).orElseGet(new Supplier<User>() {
//			//interface(Supplier)를 new할려면 익명클래스로 만들어야한다
//		@Override
//		public User get() { //Supplier가 가지고 있는 get메서드를 오버라이딩 
//			
//			return new User(); // null일경우 user빈객체를 넣어서 null이 아니게 만들어버린다 
//		}
//		}); //get():null이 리턴될리 없어,orElseGet()은 null이면 optional보고 객체를 하나만들어 달라고 하는거다  
		//Supplier<User>() 익명객체 생성 
		
		//요청:웹브라우저
		//user 객체 = 자바 오브젝트
		//변환 (웹브라우저가 이해할 수 있는 데이터) -> josn
		//스프링부트 = messageconverter라는 애가 응답시에 자동 작동
		//만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		//user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}
	
	
	//위에꺼를 람다식으로 수정
//	@GetMapping("/dummy/user/{id}")
//	public User detail(@PathVariable int id ) {
//		User user = userrepository.findById(id).orElseThrow(()->{//()->익명으로 처리
//			return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
//		});
//	
//		return user;
//	}
//	
	
	
	
	
	
	
	
	@PostMapping("/dummy/join")
	public String join(User user) { //key=value(규칙)
		System.out.println("id:"+user.getId());
		System.out.println("username:"+user.getUsername());
		System.out.println("password:"+user.getPassword());
		System.out.println("email:"+user.getEmail());
		System.out.println("role:"+user.getRole());
		System.out.println("create:"+user.getCreateDate());
		
		user.setRole(RoleType.USER); 
		userrepository.save(user); //role이 안들어감
		return "회원가입이 완료되었습니다";
	}
}
