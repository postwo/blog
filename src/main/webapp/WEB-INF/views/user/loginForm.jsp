<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<!--  ../ 한칸위로 -->

<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username</label> <input type="text" name="usernmae" class="form-control" placeholder="Enter username" id="username">
		</div>
				
		<div class="form-group">
			<label for="passowrd">Password</label> <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form>

</div>

<!-- <script src="/js/user.js"></script>  이제 사용안할거기 때문에 from안으로 로그인 버튼을 넣어준다-->

<%@ include file="../layout/footer.jsp"%>

