<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberDao" %>
<%@ page import="service.Member" %>
<!-- 2018.06.26 28�� ������ ������. -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertMember���� ȭ��</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("euc-kr");
			//ȭ�鿡��  �Է��ϴ� �̸����� �ѱ��̱⶧���� euc-kr�� �־� �ѱ��� �ȱ������� Encoding�� �־����ϴ�..
			Member member = new Member();
			//MemberŬ������ ���� �ֱ� ����  Member()������ �޼ҵ带 ���� ���οü�� ������ �� �� ���ּҰ��� Member�������� ����� ������ �Ҵ��� ���׽��ϴ�.  
			String memberName = null;
			int memberAge =0;
			if(request.getParameter("member_name") == "" && request.getParameter("member_age") == "") {
				System.out.println("�Է°��� �����ϴ�.");
				response.sendRedirect("./insertMemberForm.jsp");
				
			}else {
				memberName = request.getParameter("member_name");
				memberAge = Integer.parseInt(request.getParameter("member_age"));
				System.out.println(memberName +"<-getMember_name");
				System.out.println(memberAge +"<-getMember_age");
				/* ȭ�鿡�� �Է��� ���� �ޱ����� request��ü�� ����Ǿ��ִ� getParameter�޼ҵ�� ���� String�������� ���� �޾ƿ� ������ ������ 
				age���� int�����̱� ������ Integer��Ű���� ����Ǿ��ִ� parseInt�޼ҵ�� ���� int�������� ��ȯ ���� ������ �����߽��ϴ�.*/

				member.setMemberName(memberName);
				member.setMemberAge(memberAge);
				//insert���� �����ϱ� ���� member������ ����ִ� �ּҰ��� ã�ư� setMemberOoo�޼ҵ带 ����  ȭ�鿡�� �޾ƿ°��� setting�� �߽��ϴ�.
				
				MemberDao MemberAction = new MemberDao();
				//MemberDao()������ �޼ҵ带 ���� ���ο� ��ü�� ������ �ϰ� ������ �ּҰ��� MemberDaoŬ������������ ����Ǿ� �ִ� MemberAction������ ������ �߽��ϴ�.
				MemberAction.insertMember(member);
				//MemberAction������ ����ִ� �ּҰ��� �� insertMember�޼ҵ带 ���� member������ ������ �߽��ϴ�.
				
			}
			
		%>
	</body>
</html>