package memberDTO;

public class MemberAndScore {

	private String memberName;
	private int memberNo;
	private int memberAge;
	private int memberScoreNo;
	private int score;
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getMemberScoreNo() {
		return memberScoreNo;
	}
	public void setMemberScoreNo(int memberScoreNo) {
		this.memberScoreNo = memberScoreNo;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getMemberAge() {
		return memberAge;
	}
	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}
}
