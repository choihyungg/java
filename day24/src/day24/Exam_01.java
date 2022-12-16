package day24;

import java.util.HashSet;

class Member {
	String memberId, name;
	
	public Member(String num, String name) {
		this.memberId = memberId;
		this.name = name;
	}

	public String getMemberId() {
		return memberId;
	}

	@Override
	public String toString() {
		return memberId + ":" + name;
	}

	@Override
	public int hashCode() {
		return memberId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Member) {
			Member member = (Member)obj;
			if(this.memberId == member.memberId) {
				return true;
			}
			else return false;
		}
		return false;
	}	
}

public class Exam_01 {
	//[400:정약용, 100:김유신, 200:강감찬, 300:이순신]

	public static void main(String[] args) {
		HashSet<Member> set = new HashSet<Member>();
		set.add(new Member("100", "김유신"));
		set.add(new Member("200", "강감찬"));
		set.add(new Member("300", "이순신"));
		set.add(new Member("400", "정약용"));
		set.add(new Member("100", "홍길동"));
		
		System.out.println(set);
	}
}
