package school;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManage {
	private Scanner input = new Scanner(System.in);
	private ArrayList<Student> students = new ArrayList();
	private String[] className = {"JAVA", "PYTHON", "C", "test"};
	
	Student findStudentInform(int studentNumber) {
		for (Student student : students) {
			if(student.getStudentNumber() == studentNumber) {
				System.out.println("�ش� �л��� ������ ������ �����ϴ�.");
				System.out.println("�̸�: " + student.getName());
				System.out.println("��ȭ��ȣ: " + student.getPhoneNumber());
				System.out.println("�޸�: " + student.getMemo());
				return student;
			}
		} return null;
	}

	// 1. �л� �߰� �� ����
	public void addStudent() {
		System.out.print("�л��� �й��� �Է� �ϼ���. >>>");
		int studentNumber = input.nextInt();
		
		//�Է��� �й����� ���ο� �л��� ����
		Student newStudent = findStudentInform(studentNumber);
		if(newStudent == null) {
			newStudent = new Student(studentNumber);
			students.add(newStudent); // �л� ��ü�� ArrayList�� ����
		}
		
		System.out.print("�̸��� �Է� �ϼ���. >>>");
		newStudent.setName(input.next()); // ���ڿ� �Է� �޾Ƽ� setName�� ����
		System.out.print("��ȭ ��ȣ�� �Է� �ϼ���. >>>");
		newStudent.setPhoneNumber(input.next());
		System.out.print("�޸� �Է� �ϼ���. >>>");
		newStudent.setMemo(input.next());
		
		System.out.println("�л� �߰��� �Ϸ� �Ǿ����ϴ�." + "\n");
	}

	//2. �л� ���� �޴�
	public void removeStudent() {
		System.out.print("�л��� �й��� �Է� �ϼ���. >>>");
		int studentNumber = input.nextInt(); //�й� �Է¹���
		
		Student newStudent = findStudentInform(studentNumber);
		if(newStudent == null) { //���� ������ �л��� ������ ���ٸ�
			System.out.println("Error : �л��� �������� �ʽ��ϴ�.!!!");
			return; //�޼ҵ� ����
		}
		
		students.remove(newStudent); //�ش� �л��� ����
		System.out.println("�л� ������ �Ϸ�Ǿ����ϴ�.");
	}
	

	//3. ������û �޴�
	public void addClass() {
		System.out.print("�л��� �й��� �Է� �ϼ���. >>>");
		int studentNumber = input.nextInt(); //�й� �Է¹���
		
		Student newStudent = findStudentInform(studentNumber);
		if(newStudent == null) {//���� ������ �л��� ������ ���ٸ�
			System.out.println("Error : �л��� �������� �ʽ��ϴ�.!!!");
			return; //�޼ҵ� ����
		}
		
		while(true) {	
			System.out.println("�޴��� �����ϼ���: 1. ������û / 2. �������� / 3. ����");
			int classMenu = input.nextInt();
			switch(classMenu) {
			case 1:
				setClass("������û �� ������ �����ϼ���" , newStudent, true);
				break;
			case 2:
				setClass("�������� �� ������ �����ϼ���" , newStudent, false);
				break;
			case 3:
				return;
			}
		}
	}

	private void setClass(String message, Student newStudent, boolean check) {
		System.out.println(message);
		printClass();
		int index = input.nextInt() -1;
		newStudent.setClassCheck(index, check);
		
		if(!check) { //�������� �� �����̶��
			newStudent.setClassScore(index, 0); //������ 0���� �ǵ���
		}
	}
	
	void printClass() {
		for (int i=0; i<className.length; i++) {
			System.out.print(i+1 + "." + className[i] + " ");
		}
		System.out.println();
	}

	//4. ���� �Է� �޴�
	public void setScore() {
		System.out.print("�л��� �й��� �Է� �ϼ���. >>>");
		int studentNumber = input.nextInt(); //�й� �Է¹���
		
		Student newsStudent = findStudentInform(studentNumber);
		if(newsStudent == null) { //���� ������ �л��� ������ ���ٸ�
			System.out.println("Error : �л��� �������� �ʽ��ϴ�.!!!");
			return; //�޼ҵ� ����
		}
		
		while (true) {
			System.out.print("������ �Է�/������ ������ �����ϼ���." + "/ 4. ����");
			printClass();
			System.out.print("/ 4. ����");
			int classMenu = input.nextInt();
			if(classMenu == 4) { //���� üũ
				break; //while�� ����
			}
//			if (!newsStudent.getClassCheck()[classMenu-1]) { //�̽�û üũ
//				System.out.println(className[classMenu-1] + "������ �̽�û �����Դϴ�!");
//				continue; //�̽�û�̱� ������ ó������ ���ư���.
//			}
			System.out.print("������ �Է��ϼ���. >>>");
			int score = input.nextInt();
			if(score < 0 || score > 100) { //������ 0 - 100 �������� üũ
				System.out.println("Error: ������ 0���� 100������ ���ڸ� �Է��� �ּ���.");
				continue;
			}
			//�������� ����� ������ �Էµ� ���
			newsStudent.setClassScore(classMenu-1, score); //�ش� �л��� Score�� ������Ʈ �Ѵ�.
			System.out.println(className[classMenu-1] + "���� �Է��� �Ϸ� �Ǿ����ϴ�."); //���
			
		}
	}

	//5. �л� ���� ��ȸ �޴�
	public void informStudent() {
		System.out.println("�޴��� ���� ���ּ���. 1. Ư���л��� / 2. ��ü �л�");
		int menu = input.nextInt();
		switch(menu) {
		case 1: //Ư�� �л��� ���� ����
			One();
			break;
		case 2: //��ü �л��� ���� ����
			printStudentInfo();
			break;
		}
		
	}
	
	void One() {
		System.out.print("�л��� �й��� �Է� �ϼ���");
		int studentNumber = input.nextInt(); //�й� �Է¹���
		
		Student newStudent = findStudentInform(studentNumber);
		if(newStudent == null) { //���� ������ �л��� ������ ���ٸ�
			System.out.println("Error : �л��� �������� �ʽ��ϴ�.!!!");
			return; //�޼ҵ� ����
		}
		printStudentInfo();
	}
	
//	void All() {
//		printStudentInfo();
//	}
	
	
	void printStudentInfo() {
		for (Student student : students) {
			System.out.println("�й� : " + student.getStudentNumber());
			System.out.println("�̸� : " + student.getName());
			System.out.println("��ȭ��ȣ : " + student.getPhoneNumber());
			System.out.println("�޸� : " + student.getMemo());
			System.out.print("���� �������� ����� ���� >>>");
//			boolean[] classCheck = student.getClassCheck();
//			int[] classScore = student.getClassScore();
//			for(int i=0; i<classCheck.length; i++) {
//				if(classCheck[i]) {
//					System.out.println("�����:" + className[i] + " / ����" + classScore[i]);
				}
//			}
//		}
	}
}