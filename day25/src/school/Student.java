package school;

import java.util.ArrayList;
import java.util.LinkedList;


public class Student extends Person{
	private int StudentNumber; //�й�
	//�л��� ����ִ� ������ ���� (�ڹ�, ���̽�, C)
//	private boolean[] classCheck = {false, false, false}; //���� ��û�� �ϸ� true�� ����
	private LinkedList classCheck = new LinkedList();
//	private ArrayList<Boolean> classCheck;
//	private int[] classScore = {0, 0, 0}; //�� ���� ����
	private LinkedList classScore = new LinkedList();
	
	//Student�� ������
	public Student(int studentNumber) {
		// �й��� �Ű� ������ �޾Ƽ� ���� �л��� �й��� �����Ѵ�.
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
