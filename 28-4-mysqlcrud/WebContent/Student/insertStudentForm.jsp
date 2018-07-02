<!-- 28�� �̿��� 2018. 6. 25(��)insertStudentForm.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel= "stylesheet" type= "text/css" href="../css/studentForm.css">
<script type="text/javascript">
window.addEventListener("load", function(){
	// ��Ģ�� �ƴ����� �ڹٽ�ũ��Ʈ �ڵ尡 head�� ��ġ�ϰ� �ϱ����� �̺�Ʈó���� ���ؼ� ������ ������(load) ���� �ڹٽ�ũ��Ʈ �ڵ尡 �������� �ϱ� ���� �ڵ���.
		console.log("window load");		// test code
		var signup = document.getElementById("signup");				//signup�̶�� id�� ���� html�±׸� signup������ �����Ͽ���.
		signup.addEventListener("click", function(){				//signupd�̶�� ������ ��� �±׸� Ŭ�������� �̺�Ʈ�� �߻��Ѵ�.
			console.log("signup button click event");
			
			// helper
			var namehelper = document.getElementById("namehelper");	//namehelper��� id�� ���� html�±׸� namehelper������ �����Ͽ���.
			// helper ��
			var nameValue =  document.getElementById("name").value;	//name��� id�� ���� html�±��� value�� ��� ���� nameValue������ �����Ͽ���
			var ageValue = document.getElementById("age").value;	//age��� id�� ���� html�±��� value�� ��� ���� ageValue������ �����Ͽ���
			var birthValue = document.getElementById("birth").value;//birth��� id�� ���� html�±��� value�� ��� ���� birthValue������ �����Ͽ���
			console.log(typeof ageValue);
			if(nameValue.length = 0 || nameValue.length > 5){
				namehelper.innerHTML ="�̸��� �ʹ��� ª�ų� ��ϴ�."
				return;
			}else{
				var birthYear = birthValue.substring(0, 4);				// birth�� �� 2000-00-00�� 0��°(2) ���� ���� 4��°(-) ������ �������� �޼ҵ�
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
<title>�л� �Է� ��</title>
</head>
<body>
	<div id="insertStudentForm">�л����
		<form action="<%=request.getContextPath()%>/insertStudentAction.jsp" method="post" id="insertStudentForm">
		<!-- placeholder�Ӽ� : ª�� ��Ʈ�� ª�� ���� ��Ÿ�� ����ڰ� �����͸� �Է��ϴµ� ������ �ֱ� ���� ����ϴ� �Ӽ� -->
			<ul>
				<li>
					<label for="name">�̸�</label>	
					<input type="text" id="name" name="studentName" placeholder="�ѱ� 2�� �̻�, 5�� ����" required>
					<span id="namehelper" class="helper"></span>
				</li>
				<li>	
					<label for="birth">�������</label>	
					<input type="date" id="birth" name="studentBirth" required>
				</li>
				<li>	
					<label for="age">����</label>	
					<input type="text" id="age" name="studentAge" placeholder="������� �Է½� �ڵ��Է�" readonly>
				</li>
				<li>
					<label for="address">�ּ�</label>	
					<input type="text" id="address" name="studentAddrContent" required>	
				</li>
				<li>	
					<input type="button" id="signup" value="���">
				</li>	
			</ul>	
		</form>
	</div>
</body>
</html>