package service;

public class MemberAddr {
	private int memberAddrNo;
	private int memberNo;
	private String memberAddrContent;
	//private접근 지정자를 통해 기본타입으로 통해 선언한 변수들을 MemberAddr클래스에만 사용 할수 있도록 지정했습니다. 
	

	public int getMemberAddrNo() {
		return memberAddrNo;
	}
	//getMemberAddrNo()메서드를 실행이 되면 memberAddrNo변수에 들어있는 값을 리턴 시켜주도록 했습니다.
	public void setMemberAddrNo(int memberAddrNo) {
		this.memberAddrNo = memberAddrNo;
	}
	//setMemberAddrNo메서드에 들어있는 memberAddrNo변수값을 this로 통해 전역변수로 설정되어있는 memberAddrNo변수에 할당 시켜줍니다. 
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
