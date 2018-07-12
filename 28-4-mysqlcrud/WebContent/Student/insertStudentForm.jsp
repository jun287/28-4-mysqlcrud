<!-- 28기 이원상 2018. 7. 12(목) 최종수정, insertStudentForm.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/studentForm.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/script/insertStudentForm.js">
</script>
<title>학생 입력 폼</title>
</head>
<body>
	<div>학생등록
		<form action="<%=request.getContextPath()%>/Student/insertStudentAction.jsp" method="post" id="insertStudentForm">
		<!-- placeholder속성 : 짧은 힌트나 짧은 구를 나타내 사용자가 데이터를 입력하는데 도움을 주기 위해 사용하는 속성 -->
			<ul>
				<li>
					<label for="name">이름</label>	
					<input type="text" id="name" name="studentName" placeholder="한글 2자 이상, 6자 이하" required>
					<span id="namehelper" class="helper"></span>
				</li>
				<li>	
					<label for="birth">생년월일</label>	
					<input type="date" id="birth" name="studentBirth" required>
					<span id="birthhelper" class="helper"></span>
				</li>
				<li>	
					<label for="age">나이</label>	
					<input type="text" id="age" name="studentAge" placeholder="생년월일 입력시 자동입력" readonly>
				</li>
				<li>
					<label for="address">주소</label>	
					<input type="text" id="address" name="studentAddrContent" required>	
				</li>
				<li>	
					<button type="button" id="signup" >등록</button>
					<button type="button" id="list">목록으로</button>
				</li>	
			</ul>	
		</form>
	</div>
</body>
</html>