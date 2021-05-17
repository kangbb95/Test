package member.model.vo;

public class Member {
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String phone;
	private int memberLevel;
	private String memberEmail;
	private String memberGenre;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(int memberNo, String memberId, String memberPw, String memberName, String phone, int memberLevel,
			String memberEmail, String memberGenre) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.phone = phone;
		this.memberLevel = memberLevel;
		this.memberEmail = memberEmail;
		this.memberGenre = memberGenre;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberGenre() {
		return memberGenre;
	}
	public void setMemberGenre(String memberGenre) {
		this.memberGenre = memberGenre;
	}
	
}
