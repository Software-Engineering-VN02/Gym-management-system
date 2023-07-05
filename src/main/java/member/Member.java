package main.java.member;

public class Member {

	private int memberId; 
	
	private String memberName;
	
	private String memberGender;
	
	private int memberAge;
	
	private int memberHeight;
	
	private int memberWeight;
	
	private String memberPhone;
	
	public Member(int memberId, String memberName, String memberGender, int memberAge, int memberHeight,
			int memberWeight, String memberPhone) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberGender = memberGender;
		this.memberAge = memberAge;
		this.memberHeight = memberHeight;
		this.memberWeight = memberWeight;
		this.memberPhone = memberPhone;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public int getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}

	public int getMemberHeight() {
		return memberHeight;
	}

	public void setMemberHeight(int memberHeight) {
		this.memberHeight = memberHeight;
	}

	public int getMemberWeight() {
		return memberWeight;
	}

	public void setMemberWeight(int memberWeight) {
		this.memberWeight = memberWeight;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	
}
