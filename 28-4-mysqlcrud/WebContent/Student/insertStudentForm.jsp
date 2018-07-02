<!-- 28기 이원상 2018. 6. 25(월)insertStudentForm.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel= "stylesheet" type= "text/css" href="../css/studentForm.css">
<script type="text/javascript">
window.addEventListener("load", function(){
	// 원칙은 아니지만 자바스크립트 코드가 head에 위치하게 하기위해 이벤트처리를 통해서 문서가 읽히고(load) 나서 자바스크립트 코드가 읽히도록 하기 위한 코드임.
		console.log("window load");		// test code
		var signup = document.getElementById("signup");				//signup이라는 id를 가진 html태그를 signup변수에 대입하였음.
		signup.addEventListener("click", function(){				//signupd이라는 변수에 담긴 태그를 클릭했을때 이벤트가 발생한다.
			console.log("signup button click event");
			
			// helper
			var namehelper = document.getElementById("namehelper");	//namehelper라는 id를 가진 html태그를 namehelper변수에 대입하였음.
			// helper 끝
			var nameValue =  document.getElementById("name").value;	//name라는 id를 가진 html태그의 value의 담긴 값을 nameValue변수에 대입하였음
			var ageValue = document.getElementById("age").value;	//age라는 id를 가진 html태그의 value의 담긴 값을 ageValue변수에 대입하였음
			var birthValue = document.getElementById("birth").value;//birth라는 id를 가진 html태그의 value의 담긴 값을 birthValue변수에 대입하였음
			console.log(typeof ageValue);
			if(nameValue.length = 0 || nameValue.length > 5){
				namehelper.innerHTML ="이름이 너무나 짧거나 깁니다."
				return;
			}else{
				var birthYear = birthValue.substring(0, 4);				// birth의 값 2000-00-00을 0번째(2) 문자 포함 4번째(-) 미포함 가져오는 메소드
				console.log(birthYear+"<--birthYear");
				var today = new Date();
				var thisYear = today.getFullYear();
				console.log(thisYear+"<--thisYear");
				document.getElementById("age").value = ((thisYear+1)-birthYear);
				console.log(document.getElementById("age").value+"<--value");
				document.getElementById("insertStudentForm").submit();
			}				
		})
})
</script>
<title>학생 입력 폼</title>
</head>
<body>
	<div id="insertStudentForm">학생등록
		<form action="<%=request.getContextPath()%>/insertStudentAction.jsp" method="post" id="insertStudentForm">
		<!-- placeholder속성 : 짧은 힌트나 짧은 구를 나타내 사용자가 데이터를 입력하는데 도움을 주기 위해 사용하는 속성 -->
			<ul>
				<li>
					<label for="name">이름</label>	
					<input type="text" id="name" name="studentName" placeholder="한글 2자 이상, 5자 이하" required>
					<span id="namehelper" class="helper"></span>
				</li>
				<li>	
					<label for="birth">생년월일</label>	
					<input type="date" id="birth" name="studentBirth" required>
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
					<input type="button" id="signup" value="등록">
				</li>	
			</ul>	
		</form>
	</div>
</body>
</html>