package school;

import java.util.ArrayList;
import java.util.LinkedList;


public class Student extends Person{
	private int StudentNumber; //학번
	//학생이 듣고있는 수업의 정보 (자바, 파이썬, C)
//	private boolean[] classCheck = {false, false, false}; //수강 신청을 하면 true로 변경
	private LinkedList classCheck = new LinkedList();
//	private ArrayList<Boolean> classCheck;
//	private int[] classScore = {0, 0, 0}; //각 과목 성적
	private LinkedList classScore = new LinkedList();
	
	//Student의 생성자
	public Student(int studentNumber) {
		// 학번을 매개 변수로 받아서 현재 학생의 학번을 설정한다.
		this.StudentNumber = studentNumber;
//		classCheck = new LinkedList();
	}

//	public boolean[] getClassCheck() {
//		return classCheck;
//	}
//	
	
	
	public void setClassCheck(int index, boolean check) {
		for(index=0; index<classCheck.size(); index++) {
		classCheck.add(index, false);
		}
	}
	
	public LinkedList getClassScore() {
		return classScore;
	}

	public void setClassScore(int index, int score) {
		for(index=0; index<classCheck.size(); index++) {
			classCheck.add(index, score);
		}
	}
	
//	public int[] getClassScore() {
//		return classScore;
//	}
//	
//	public void setClassScore(int index, int score) {
//		this.classScore[index] = score;
//	}
	
	

	public int getStudentNumber() {
		return StudentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		StudentNumber = studentNumber;
	}

//	public boolean[] getClassCheck() {
//		return classCheck[];
//	}
	
//	void addClass(int index, boolean check) {
//		classCheck.add(index, check);
//	}
//

	
	
	

}
