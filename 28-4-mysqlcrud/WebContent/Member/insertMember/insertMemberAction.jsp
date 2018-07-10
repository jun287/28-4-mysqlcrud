<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberDao" %>
<%@ page import="service.Member" %>
<!-- 2018.06.26 28기 개발자 전재현. -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertMember실행 화면</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("euc-kr");
			//화면에서  입력하는 이름값이 한글이기때문에 euc-kr을 넣어 한글이 안깨지도록 Encoding을 넣었습니다..
			Member member = new Member();
			//Member클래스에 값을 넣기 위해  Member()생성자 메소드를 통해 새로운객체를 생성을 한 후 그주소값을 Member형식으로 선언된 변수에 할당을 시켰습니다.  
			String memberName = null;
			int memberAge =0;
			if(request.getParameter("member_name") == "" && request.getParameter("member_age") == "") {
				System.out.println("입력값이 없습니다.");
				response.sendRedirect("./insertMemberForm.jsp");
				
			}else {
				memberName = request.getParameter("member_name");
				memberAge = Integer.parseInt(request.getParameter("member_age"));
				System.out.println(memberName +"<-getMember_name");
				System.out.println(memberAge +"<-getMember_age");
				/* 화면에서 입력한 값을 받기위해 request객체에 내장되어있는 getParameter메소드로 통해 String형식으로 값을 받아와 대입을 했으며 
				age값은 int형식이기 때문에 Integer패키지에 내장되어있는 parseInt메소드로 통해 int형식으로 변환 시켜 변수에 대입했습니다.*/

				member.setMemberName(memberName);
				member.setMemberAge(memberAge);
				//insert문을 실행하기 위해 member변수에 들어있는 주소값을 찾아가 setMemberOoo메소드를 통해  화면에서 받아온값을 setting을 했습니다.
				
				MemberDao MemberAction = new MemberDao();
				//MemberDao()생성자 메소드를 통해 새로운 객체를 생성을 하고 생성된 주소값을 MemberDao클래스형식으로 선언되어 있는 MemberAction변수에 대입을 했습니다.
				MemberAction.insertMember(member);
				//MemberAction변수에 들어있는 주소값에 들어가 insertMember메소드를 통해 member변수를 실행을 했습니다.
				
			}
			
		%>
	</body>
</html>