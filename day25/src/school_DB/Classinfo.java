package school_DB;

public class Classinfo extends Student {
	private int code;  //���� �ڵ�
	private String subject; //�����
	
	public Classinfo() {}
	
	public Classinfo(int studentNumber, int code, String subject) {
		super(studentNumber);
		this.code = code;
		this.subject = subject;
	}
	
	public Classinfo(int code, String subject) {
		this.code = code;
		this.subject = subject;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

}
