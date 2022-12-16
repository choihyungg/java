package school_DB;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentManage {
	private Scanner input = new Scanner(System.in);
	private StudentDAO studentDAO;
	private ClassInfoDAO classInfoDAO;
	private StudentClassDAO studentClassDAO;
	
	public StudentManage() {
		studentDAO = new StudentDAO();
		classInfoDAO = new ClassInfoDAO();
		studentClassDAO = new StudentClassDAO();
		input = new Scanner(System.in);
	}
	
	// 1. �л� �߰�
	public void addStudent() {
		Student student = new Student();
		
		System.out.print("�л��� �й��� �Է� �ϼ���. >>>");
		int studentNumber = input.nextInt();
		student.setStudentNumber(studentNumber);
		
		System.out.print("�̸��� �Է� �ϼ���. >>>");
		student.setName(input.next()); // ���ڿ� �Է� �޾Ƽ� setName�� ����
		
		System.out.print("��ȭ ��ȣ�� �Է� �ϼ���. >>>");
		student.setPhoneNumber(input.next());
		
		System.out.print("�޸� �Է� �ϼ���. >>>");
		student.setMemo(input.next());
		
		if (studentDAO.insertStuent(student)) {
			System.out.println("�л� �߰��� �Ϸ� �Ǿ����ϴ�." + "\n");
		}
		else {
			System.out.println("�л� �߰��� �����߽��ϴ�." + "\n");
		}
	}

	
	//2. �л� ����
	public void removeStudent() throws SQLException {		
		System.out.print("�л��� �й��� �Է� �ϼ���. >>>");
		int studentNumber = input.nextInt(); //�й� �Է¹���
		
		StudentDAO studentDAO = new StudentDAO();
		if(!studentDAO.isStudent(studentNumber)) { //���� ������ �л��� ������ ���ٸ�
			System.out.println("Error : �л��� �������� �ʽ��ϴ�.!!!");
			return;
		}
		studentDAO.removeStudent(studentNumber);
	}
	

	//3. ������û �޴�
	public void addClass() throws SQLException {

		while(true) {	
			System.out.println("�޴��� �����ϼ���: 1. ������û / 2. �������� / 3. ����");
			int classMenu = input.nextInt();
			switch(classMenu) {
			case 1:
				appClass();
				break;
			case 2:
				cancelClass();
				break;
			case 3:
				return;
			}
		}
	}
	
	//������û
	private void appClass() {
		System.out.print("�л��� �й��� �Է� �ϼ���. >>>");
		int studentNumber = input.nextInt(); //�й� �Է¹���
		
		StudentDAO studentDAO = new StudentDAO();
		if(!studentDAO.isStudent(studentNumber)) { //���� ������ �л��� ������ ���ٸ�
			System.out.println("Error : �л��� �������� �ʽ��ϴ�.!!!");
			return; //�޼ҵ� ����
		}
		
		StudentClass studentclass = new StudentClass();
		System.out.println("���� ��û�� ������ �ڵ带 �Է��ϼ���");
		printClass();
		int code = input.nextInt();
		
		ArrayList<Classinfo> list = classInfoDAO.AllClass();
		ArrayList<Classinfo> searchList = new ArrayList<>();
		for (Classinfo classinfo : list) {
			if (classinfo.getCode() == code) {
				searchList.add(classinfo);
				studentclass.setStudentNumber(studentNumber);
				studentclass.setCode(classinfo.getCode());
				studentclass.setSubject(classinfo.getSubject());
				studentclass.setScore(0);
				if (studentClassDAO.insertStudentclass(studentNumber, code, classinfo.getSubject(), studentclass.getScore())) {
					System.out.println("������û�� �Ϸ� �Ǿ����ϴ�." + "\n");
				}
				else {
					System.out.println("���� ��û�� �����߽��ϴ�." + "\n");
					return;
				}
			}
		}	
	}
	
	//�������
	private void cancelClass() throws SQLException {
		printStudentClass();
		System.out.println("���� ������ ������ �ڵ带 �Է��ϼ���");
		
		int code = input.nextInt();
		
		if(!studentClassDAO.isStudentclass(code)) { //���� ������ �л��� ������ ���ٸ�
			System.out.println("Error : ���� �ڵ尡 �������� �ʽ��ϴ�.!!!");
			return; //�޼ҵ� ����
		}
		studentClassDAO.cancelClass(code);
	}
	
	//���� ��û�� ���� ���
	private void printStudentClass() {

		System.out.print("�л��� �й��� �Է� �ϼ���. >>>");
		int studentNumber = input.nextInt(); //�й� �Է¹���
		
		StudentDAO studentDAO = new StudentDAO();
		if(!studentDAO.isStudent(studentNumber)) { //���� ������ �л��� ������ ���ٸ�
			System.out.println("Error : �л��� �������� �ʽ��ϴ�.!!!");
			return; //�޼ҵ� ����
		}

		int cnt = 0;
		ArrayList<StudentClass> classes = studentClassDAO.AllStudentClass();
		for(StudentClass studentClass : classes) {
			if(studentNumber == studentClass.getStudentNumber()) {
				cnt++;
				System.out.println("[" + cnt + "]" + " ���� �ڵ� : " + studentClass.getCode() + ", ����� : " + studentClass.getSubject());
			}
		}
		System.out.println();
}

	//6. �������� ����
	public void classInfo() throws SQLException {
		
		while(true) {	
			System.out.println("�޴��� �����ϼ���: 1. ���� ��� / 2. ���� ���� / 3. ���� ��� / 4. ����");
			int classMenu = input.nextInt();
			switch(classMenu) {
			case 1:
				makeClass();
				break;
			case 2:
				removeClass();
				break;
			case 3:
				printClass();
				break;
			case 4:
				return;
			}
		}
	}
	
	//���� ���
	private void makeClass() {
		Classinfo classinfo = new Classinfo();
		
		System.out.print("���� �ڵ带 �Է� �ϼ���. >>>");
		int code = input.nextInt();
		classinfo.setCode(code);
		
		System.out.print("������� �Է� �ϼ���. >>>");
		classinfo.setSubject(input.next());
		
		if (classInfoDAO.insertClass(classinfo)) {
			System.out.println("���� �߰��� �Ϸ� �Ǿ����ϴ�." + "\n");
		}
		else {
			System.out.println("���� �߰��� �����߽��ϴ�." + "\n");
		}
	}
	
	//���� ����
	private void removeClass() throws SQLException {
		
		System.out.print("���� �ڵ带 �Է� �ϼ���. >>>");
		int code = input.nextInt(); //���� �ڵ� �Է¹���
		
		if(!classInfoDAO.isClass(code)) { //���� ������ �л��� ������ ���ٸ�
			System.out.println("Error : ���� �ڵ尡 �������� �ʽ��ϴ�.!!!");
			return; //�޼ҵ� ����
		}
		studentDAO.removeStudent(code);
	}
	
	//���� ���
	private void printClass() {
		ArrayList<Classinfo> list = classInfoDAO.AllClass();
		int cnt = 0;
		for (Classinfo classinfo : list) {
			cnt++;
			System.out.println("[" + cnt + "]" + " ���� �ڵ� : " + classinfo.getCode() + ", ����� : " + classinfo.getSubject());
	}
}

	//4. ���� �Է� �޴�
	public void setScore() throws SQLException {
		System.out.print("�л��� �й��� �Է� �ϼ���. >>>");
		int studentNumber = input.nextInt(); //�й� �Է¹���
		
		StudentDAO studentDAO = new StudentDAO();
		if(!studentDAO.isStudent(studentNumber)) { //���� ������ �л��� ������ ���ٸ�
			System.out.println("Error : �л��� �������� �ʽ��ϴ�.!!!");
			return; //�޼ҵ� ����
		}
		
		while (true) {
			System.out.println("������ �Է�/������ ������ �����ϼ���.");
			ArrayList<StudentClass> classes = studentClassDAO.AllStudentClass();
			for(StudentClass studentClass : classes) {
				if(studentNumber == studentClass.getStudentNumber()) {
				System.out.println("���� �ڵ� : " + studentClass.getCode() + ", �������� ���� : " + studentClass.getSubject() + ", ���� : " + studentClass.getScore());
				}
			}
			
			System.out.println("0. ����");
			int classMenu = input.nextInt();
			if(classMenu == 0) { //���� üũ
				break; //while�� ����
			}
			if (!studentClassDAO.isStudentclass(classMenu)) { //�̽�û üũ
				System.out.println("�ش� ���� �ڵ�� �̽�û�Դϴ�!");
				continue; //�̽�û�̱� ������ ó������ ���ư���.
			}
			System.out.print("������ �Է��ϼ���. >>>");
			int score = input.nextInt();
			if(score < 0 || score > 100) { //������ 0 - 100 �������� üũ
				System.out.println("Error: ������ 0���� 100������ ���ڸ� �Է��� �ּ���.");
				continue;
			}
			//�������� ����� ������ �Էµ� ���
			if (studentClassDAO.insertScore(score, studentNumber, classMenu)) {
				System.out.println("���� �Է��� �Ϸ� �Ǿ����ϴ�." + "\n");
			}
			else {
				System.out.println("�����Է¿� �����߽��ϴ�." + "\n");
			}			 		
		}
	}

	//5. �л� ���� ��ȸ �޴�
	public void informStudent() {
		System.out.println("�޴��� ���� ���ּ���. 1. Ư���л��� / 2. ��ü �л�");
		int menu = input.nextInt();
		switch(menu) {
		case 1: //Ư�� �л��� ���� ����
			printOne();
			break;
		case 2: //��ü �л��� ���� ����
			printStudentInfo();
			break;
		}	
	}
	
	private void printOne() {
		System.out.print("�л��� �й��� �Է� �ϼ���");
		int studentNumber = input.nextInt(); //�й� �Է¹���
		
		ArrayList<Student> list = studentDAO.All();
		ArrayList<Student> searchList = new ArrayList<>();
		for (Student student : list) {
			if (student.getStudentNumber() == studentNumber) {
				searchList.add(student);
			}
		}
		if (searchList.size() < 1) {
			System.out.println("�˻��� �л��� �����ϴ�.");
			return;
		}
		else {
			System.out.println("�˻��� �л��� ������ �Ʒ��� �����ϴ�.");
			System.out.println("�й�\t�̸�\t��ȭ��ȣ\t�޸�");
			for (Student student : searchList) {
				System.out.println(student);
			}
		}
}
	
	private void printStudentInfo() {
		ArrayList<Student> list = studentDAO.All();
		for (Student student : list) {
			System.out.println("�й� : " + student.getStudentNumber());
			System.out.println("�̸� : " + student.getName());
			System.out.println("��ȭ��ȣ : " + student.getPhoneNumber());
			System.out.println("�޸� : " + student.getMemo());
			
			ArrayList<StudentClass> classes = studentClassDAO.AllStudentClass();
			for(StudentClass studentClass : classes) {
				if(student.getStudentNumber() == studentClass.getStudentNumber()) {
				System.out.println("�������� ���� : " + studentClass.getSubject() + ", ���� : " + studentClass.getScore());
				}
			}
			System.out.println();
			}
		}
	}
