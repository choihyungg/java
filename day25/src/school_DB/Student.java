package school_DB;


public class Student extends Person{
	private int StudentNumber; //학번

	public Student() {}
	
	public Student(int studentNumber) {
		this.StudentNumber = studentNumber;
	}
	
	//Student의 생성자	
	public Student(int studentNumber, String name, String phoneNumber, String memo) {
		super(name, phoneNumber, memo);
		this.StudentNumber =studentNumber;

	}
	
	public int getStudentNumber() {
		return StudentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		StudentNumber = studentNumber;
	}


	@Override
	public String toString() {
		return StudentNumber + "\t" + getName() + "\t" + getPhoneNumber() + "\t" + getMemo();
	}
	
	
	
	
}
