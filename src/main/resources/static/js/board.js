let index = {
		init: function(){
			$("#btn-save").on("click",()=>{//function(){}말고 ()=> 이렇게 쓴이유는 this를 바인딩하기 위해서!!
				this.save();
			});
		},
		
		save: function(){
			
		let data={ 
			title:$("#title").val(), // id값을 찾아서 변수(username:)에 바인딩 한다
			content:$("#content").val()
		}
	
		
		$.ajax({
			type:"POST",
			url:"/api/board", 
			data: JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다");
			console.log(resp);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
		}
		
}

index.init(); 


