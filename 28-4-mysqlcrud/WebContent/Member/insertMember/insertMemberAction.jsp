<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDAO.MemberDao" %>
<%@ page import="memberDTO.Member" %>
<!-- 2018.06.26 28�� ������ ������. -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertMember���� ȭ��</title>
	</head>
	<body>
		<%
			//ȭ�鿡�� �Է��� �ѱ۰��� �ȱ������� �־�����ϴ�
			request.setCharacterEncoding("euc-kr");
			
			int memberAge = 0;
			String memberName = request.getParameter("member_name");
			System.out.println(memberName +"<-getMember_name");
			
			//ȭ�鿡�� �Է��ϴ� ���� �������� ������ ������ ���� if���� �ۼ��߽��ϴ�.
			if(request.getParameter("member_age") != "") {
				memberAge = Integer.parseInt(request.getParameter("member_age"));
				System.out.println(memberAge +"<-getMember_age");
			}else {
				
			}
			Member member = new Member();
			
			//�Ѵ� ���� ������ �ٽ� �Է�â���� �����ֵ��� ��������ϴ�
			if(request.getParameter("member_name") == "" && request.getParameter("member_age") == "") {
				System.out.println("�Է°��� �����ϴ�.");
				response.sendRedirect("./insertMemberForm.jsp");
			
			//�̸����� �ְ� age���� �����ÿ��� �ٽ� �Է�â���� �������� ��������ϴ�
			}else if(memberName != "" && memberAge == 0) {
				System.out.println("���̸� �Է����ּ���");
				response.sendRedirect("./insertMemberForm.jsp");
			
			//�̸����� ���� ���̰��� �����ÿ��� �Է�â���� �������� ��������ϴ�.
			}else if(memberName == "" && memberAge != 0) {	
				System.out.println("�̸��� �Է����ּ���");
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