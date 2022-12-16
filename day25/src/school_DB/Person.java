package school_DB;

public class Person {
	private String name; //이름
	private String phoneNumber; //전화번호
	private String memo; //메모
	
	public Person() {}
	
	public Person(String name, String phoneNumber, String memo) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.memo = memo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
