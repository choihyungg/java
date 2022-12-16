package school_DB;

public class StudentClass extends Classinfo {
	private int score;
	
	public StudentClass() {}

	public StudentClass(int StudentNumber, int code, String subject, int score) {
		super(StudentNumber, code, subject);
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
