<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDTO.MemberScore" %>
<%@ page import="memberDAO.MemberScoreDao" %>
<!-- 2018.07.09 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>득롱 화면 처리</title>
	</head>
	<body>
		<%
			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
			System.out.println(memberNo +"<- memberNO");
			
		
			//점수text박스에 값을 대입 전 실행과 대입 후 실행을 구분 하는 if문입니다.
			if(request.getParameter("memberScore") == "") {
				System.out.println("점수를입력하세요");
				
				response.sendRedirect("./insertMemberScoreForm.jsp?memberNo="+memberNo);
			}else {
				int memberScore = Integer.parseInt(request.getParameter("memberScore"));
				System.out.println(memberScore +"<- memberScore");
				MemberScoreDao memberScoreDao = new MemberScoreDao();
				int insertCheck = memberScoreDao.insertMember(memberNo ,memberScore);
				System.out.println(insertCheck +"<- insertCheck");
				
				//실행 처리에 따라 결과가 다르게 나오는 if문 입니다.
				if(insertCheck != 0) {
					System.out.println(insertCheck +"<- 저장 완료 되었습니다");
					
				}else {
					System.out.println(insertCheck +"<- 수정 완료 되었습니다");
					
				}
				response.sendRedirect("../listMember/memberList.jsp");
			}
		%>
	</body>
</html>