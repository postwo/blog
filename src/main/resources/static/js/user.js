let index = {
		init: function(){
			$("#btn-save").on("click",()=>{//function(){}말고 ()=> 이렇게 쓴이유는 this를 바인딩하기 위해서!!
				this.save();
			});
		},
		
		save: function(){
			//alert('user의 save함수 호출됨');
		let data={ 
			username:$("#username").val(), // id값을 찾아서 변수(username:)에 바인딩 한다
			password:$("#password").val(),
			email:$("#email").val()
		}
	
		//console.log(data);
		
		
		//2개는 세트다(post) 일때
		//JSON.stringify(data), // 그냥 data라고 하면 자바가 못읽기 때문에 이렇게 써야한다  //http body데이터이다
		//contentType:"application/json; charset=utf-8"
		
		
		//ajax 호출시 default가 비동기 호출이다
		// ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청을 한다
		//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다 그러므로 dataType:"json" 안써도 된다
		$.ajax({
			type:"POST",
			url:"/blog/api/user", // 호출
			data: JSON.stringify(data), // 그냥 data라고 하면 자바가 못읽기 때문에 이렇게 써야한다  //http body데이터이다
			contentType:"application/json; charset=utf-8",// body데이터가 어떤 타입인지(MIME)
			dataType:"json"// 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 buffer로 오기 때문 문자열(String)(생긴게 json이라면 ) => javascript 오브젝트로 변경			
		}).done(function(resp){
			alert("회원가입이 완료되었습니다");
			console.log(resp);
			location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		}
		
}

index.init(); 


