 /*28기 이원상 2018. 7. 12(목) insertStudentForm.js*/
window.addEventListener("load", function(){
	// 원칙은 아니지만 자바스크립트 코드가 head에 위치하게 하기위해 이벤트처리를 통해서 문서가 읽히고(load) 나서 자바스크립트 코드가 읽히도록 하기 위한 코드임.
		console.log("window load");		// test code
		var signup = document.getElementById("signup");				//signup이라는 id를 가진 html태그를 signup변수에 대입하였음.
		var list = document.getElementById("list");
		signup.addEventListener("click", function(){				//signupd이라는 변수에 담긴 태그를 클릭했을때 이벤트가 발생한다.
			console.log("signup button click event");
			
			// helper
			var nameHelper = document.getElementById("namehelper");	//namehelper라는 id를 가진 html태그를 namehelper변수에 대입하였음.
			var birthHelper = document.getElementById("birthhelper");
			// helper 끝
			var nameValue =  document.getElementById("name").value;	//name라는 id를 가진 html태그의 value의 담긴 값을 nameValue변수에 대입하였음
			var ageValue = document.getElementById("age").value;	//age라는 id를 가진 html태그의 value의 담긴 값을 ageValue변수에 대입하였음
			var birthValue = document.getElementById("birth").value;//birth라는 id를 가진 html태그의 value의 담긴 값을 birthValue변수에 대입하였음
			console.log(birthValue+"<--birthValue");
			console.log(typeof ageValue);
			if(nameValue.length <= 1 || nameValue.length > 5){
				nameHelper.innerHTML ="이름이 너무나 짧거나 깁니다."
				return;
			}else if(birthValue == null || birthValue == ""){
				birthHelper.innerHTML = "값을 입력하여주세요"	
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
		list.addEventListener("click", function(){
			location.href="studentList.jsp";
		});
})