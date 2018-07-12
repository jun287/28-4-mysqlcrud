<!-- 28기 정민수 2018. 6. 25(월)insertEmployee.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertEmployeeForm.jsp</title>
		<link rel="stylesheet" type="text/css" href="../style/insertEmployeeForm.css">
		<script>
			//로드했을 때 실행되게하기
						
			//이름,나이,가입변수가져오기
			
			//엔터키쳤을때 아닐때 값 반환하기
			
			//나이가 숫자가 아닐때 값주기
			
		</script>
	</head>
	<body>
		<!-- 회원 가입폼 -->
		<form action="./insertEmployeeAction.jsp" method="post" >
			<div id="name">
				
				<input type='text' name="name" placeholder="이름" id=namehelper>
			</div>
			<div id="age">
				
				<input type='text' name="age" placeholder="나이" id=agehelper>
				
			</div>
			<div>
				<input type="submit" value="가입" id="ok">
			</div>
		</form>
	</body>
</html>