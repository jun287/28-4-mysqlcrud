package service;

public class MemberAddr {
	private int memberAddrNo;
	private int memberNo;
	private String memberAddrContent;
	//private���� �����ڸ� ���� �⺻Ÿ������ ���� ������ �������� MemberAddrŬ�������� ��� �Ҽ� �ֵ��� �����߽��ϴ�. 
	

	public int getMemberAddrNo() {
		return memberAddrNo;
	}
	//getMemberAddrNo()�޼��带 ������ �Ǹ� memberAddrNo������ ����ִ� ���� ���� �����ֵ��� �߽��ϴ�.
	public void setMemberAddrNo(int memberAddrNo) {
		this.memberAddrNo = memberAddrNo;
	}
	//setMemberAddrNo�޼��忡 ����ִ� memberAddrNo�������� this�� ���� ���������� �����Ǿ��ִ� memberAddrNo������ �Ҵ� �����ݴϴ�. 
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberAddrContent() {
		return memberAddrContent;
	}
	public void setMemberAddrContent(String memberAddrContent) {
		this.memberAddrContent = memberAddrContent;
	}	
}
