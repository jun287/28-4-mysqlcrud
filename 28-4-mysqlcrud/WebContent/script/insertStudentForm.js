 /*28�� �̿��� 2018. 7. 12(��) insertStudentForm.js*/
window.addEventListener("load", function(){
	// ��Ģ�� �ƴ����� �ڹٽ�ũ��Ʈ �ڵ尡 head�� ��ġ�ϰ� �ϱ����� �̺�Ʈó���� ���ؼ� ������ ������(load) ���� �ڹٽ�ũ��Ʈ �ڵ尡 �������� �ϱ� ���� �ڵ���.
		console.log("window load");		// test code
		var signup = document.getElementById("signup");				//signup�̶�� id�� ���� html�±׸� signup������ �����Ͽ���.
		var list = document.getElementById("list");
		signup.addEventListener("click", function(){				//signupd�̶�� ������ ��� �±׸� Ŭ�������� �̺�Ʈ�� �߻��Ѵ�.
			console.log("signup button click event");
			
			// helper
			var nameHelper = document.getElementById("namehelper");	//namehelper��� id�� ���� html�±׸� namehelper������ �����Ͽ���.
			var birthHelper = document.getElementById("birthhelper");
			// helper ��
			var nameValue =  document.getElementById("name").value;	//name��� id�� ���� html�±��� value�� ��� ���� nameValue������ �����Ͽ���
			var ageValue = document.getElementById("age").value;	//age��� id�� ���� html�±��� value�� ��� ���� ageValue������ �����Ͽ���
			var birthValue = document.getElementById("birth").value;//birth��� id�� ���� html�±��� value�� ��� ���� birthValue������ �����Ͽ���
			console.log(birthValue+"<--birthValue");
			console.log(typeof ageValue);
			if(nameValue.length <= 1 || nameValue.length > 5){
				nameHelper.innerHTML ="�̸��� �ʹ��� ª�ų� ��ϴ�."
				return;
			}else if(birthValue == null || birthValue == ""){
				birthHelper.innerHTML = "���� �Է��Ͽ��ּ���"	
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
		list.addEventListener("click", function(){
			location.href="studentList.jsp";
		});
})