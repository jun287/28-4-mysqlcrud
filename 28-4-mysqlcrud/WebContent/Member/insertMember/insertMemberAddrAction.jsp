<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDTO.MemberAddr" %>
<%@ page import="memberDAO.MemberAddrDao" %>
<!-- 2018.07.06 28기 전재현-->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>주소 입력 처리</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("euc-kr");
			//화면창에서 입력한 한글깨짐을 방지합니다
			int getMemberNo = Integer.parseInt(request.getParameter("memberNo"));
			
			if(request.getParameter("sendContent") == "") {
				System.out.println("주소값을 입력하세요");				
				response.sendRedirect("./insertMemberAddrForm.jsp");
				//sendContent값이 공백이면 다시 Form.jsp파일로 이동시킵니다.
			}else {
				String memberAddrContent = request.getParameter("sendContent");
				//화면에서 입력한값을 사용하기위해 변수에 대입합니다.
				MemberAddrDao memberAddrDao = new MemberAddrDao();
				int getExecution = memberAddrDao.insertMemberAddr(getMemberNo ,memberAddrContent);
				System.out.println(getExecution +"<-getExecution");
				//memberAddrDao참조변수에 들어있는 주소값을 찾아가 insertMemberAddr()메서드에 getMemberNO ,memberAddrContent변수에 들어있는값을 대입후 실행하고 리턴값을 사용하기위해 getExecution변수에 대입했습니다.
				
				System.out.println("실행을 완료 했습니다");
				response.sendRedirect("../listMember/memberList.jsp");
				//실행 완료후 memberList.jsp파일로 이동시켰습니다.
			}
		%>
	</body>
</html>