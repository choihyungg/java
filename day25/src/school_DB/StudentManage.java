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
	
	// 1. 학생 추가
	public void addStudent() {
		Student student = new Student();
		
		System.out.print("학생의 학번을 입력 하세요. >>>");
		int studentNumber = input.nextInt();
		student.setStudentNumber(studentNumber);
		
		System.out.print("이름을 입력 하세요. >>>");
		student.setName(input.next()); // 문자열 입력 받아서 setName에 전달
		
		System.out.print("전화 번호를 입력 하세요. >>>");
		student.setPhoneNumber(input.next());
		
		System.out.print("메모를 입력 하세요. >>>");
		student.setMemo(input.next());
		
		if (studentDAO.insertStuent(student)) {
			System.out.println("학생 추가가 완료 되었습니다." + "\n");
		}
		else {
			System.out.println("학생 추가에 실패했습니다." + "\n");
		}
	}

	
	//2. 학생 삭제
	public void removeStudent() throws SQLException {		
		System.out.print("학생의 학번을 입력 하세요. >>>");
		int studentNumber = input.nextInt(); //학번 입력받음
		
		StudentDAO studentDAO = new StudentDAO();
		if(!studentDAO.isStudent(studentNumber)) { //만약 기존에 학생의 정보가 없다면
			System.out.println("Error : 학생이 존재하지 않습니다.!!!");
			return;
		}
		studentDAO.removeStudent(studentNumber);
	}
	

	//3. 수강신청 메뉴
	public void addClass() throws SQLException {

		while(true) {	
			System.out.println("메뉴를 선택하세요: 1. 수강신청 / 2. 수강포기 / 3. 종료");
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
	
	//수강신청
	private void appClass() {
		System.out.print("학생의 학번을 입력 하세요. >>>");
		int studentNumber = input.nextInt(); //학번 입력받음
		
		StudentDAO studentDAO = new StudentDAO();
		if(!studentDAO.isStudent(studentNumber)) { //만약 기존에 학생의 정보가 없다면
			System.out.println("Error : 학생이 존재하지 않습니다.!!!");
			return; //메소드 종료
		}
		
		StudentClass studentclass = new StudentClass();
		System.out.println("수강 신청할 과목의 코드를 입력하세요");
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
					System.out.println("수강신청이 완료 되었습니다." + "\n");
				}
				else {
					System.out.println("수강 신청에 실패했습니다." + "\n");
					return;
				}
			}
		}	
	}
	
	//수강취소
	private void cancelClass() throws SQLException {
		printStudentClass();
		System.out.println("수강 포기할 과목의 코드를 입력하세요");
		
		int code = input.nextInt();
		
		if(!studentClassDAO.isStudentclass(code)) { //만약 기존에 학생의 정보가 없다면
			System.out.println("Error : 과목 코드가 존재하지 않습니다.!!!");
			return; //메소드 종료
		}
		studentClassDAO.cancelClass(code);
	}
	
	//수강 신청한 과목 출력
	private void printStudentClass() {

		System.out.print("학생의 학번을 입력 하세요. >>>");
		int studentNumber = input.nextInt(); //학번 입력받음
		
		StudentDAO studentDAO = new StudentDAO();
		if(!studentDAO.isStudent(studentNumber)) { //만약 기존에 학생의 정보가 없다면
			System.out.println("Error : 학생이 존재하지 않습니다.!!!");
			return; //메소드 종료
		}

		int cnt = 0;
		ArrayList<StudentClass> classes = studentClassDAO.AllStudentClass();
		for(StudentClass studentClass : classes) {
			if(studentNumber == studentClass.getStudentNumber()) {
				cnt++;
				System.out.println("[" + cnt + "]" + " 과목 코드 : " + studentClass.getCode() + ", 과목명 : " + studentClass.getSubject());
			}
		}
		System.out.println();
}

	//6. 수강과목 관리
	public void classInfo() throws SQLException {
		
		while(true) {	
			System.out.println("메뉴를 선택하세요: 1. 과목 등록 / 2. 과목 삭제 / 3. 과목 목록 / 4. 종료");
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
	
	//과목 등록
	private void makeClass() {
		Classinfo classinfo = new Classinfo();
		
		System.out.print("과목 코드를 입력 하세요. >>>");
		int code = input.nextInt();
		classinfo.setCode(code);
		
		System.out.print("과목명을 입력 하세요. >>>");
		classinfo.setSubject(input.next());
		
		if (classInfoDAO.insertClass(classinfo)) {
			System.out.println("과목 추가가 완료 되었습니다." + "\n");
		}
		else {
			System.out.println("과목 추가에 실패했습니다." + "\n");
		}
	}
	
	//과목 삭제
	private void removeClass() throws SQLException {
		
		System.out.print("과목 코드를 입력 하세요. >>>");
		int code = input.nextInt(); //과목 코드 입력받음
		
		if(!classInfoDAO.isClass(code)) { //만약 기존에 학생의 정보가 없다면
			System.out.println("Error : 과목 코드가 존재하지 않습니다.!!!");
			return; //메소드 종료
		}
		studentDAO.removeStudent(code);
	}
	
	//과목 출력
	private void printClass() {
		ArrayList<Classinfo> list = classInfoDAO.AllClass();
		int cnt = 0;
		for (Classinfo classinfo : list) {
			cnt++;
			System.out.println("[" + cnt + "]" + " 과목 코드 : " + classinfo.getCode() + ", 과목명 : " + classinfo.getSubject());
	}
}

	//4. 성적 입력 메뉴
	public void setScore() throws SQLException {
		System.out.print("학생의 학번을 입력 하세요. >>>");
		int studentNumber = input.nextInt(); //학번 입력받음
		
		StudentDAO studentDAO = new StudentDAO();
		if(!studentDAO.isStudent(studentNumber)) { //만약 기존에 학생의 정보가 없다면
			System.out.println("Error : 학생이 존재하지 않습니다.!!!");
			return; //메소드 종료
		}
		
		while (true) {
			System.out.println("성적을 입력/수정할 과목을 선택하세요.");
			ArrayList<StudentClass> classes = studentClassDAO.AllStudentClass();
			for(StudentClass studentClass : classes) {
				if(studentNumber == studentClass.getStudentNumber()) {
				System.out.println("과목 코드 : " + studentClass.getCode() + ", 수강중인 과목 : " + studentClass.getSubject() + ", 성적 : " + studentClass.getScore());
				}
			}
			
			System.out.println("0. 종료");
			int classMenu = input.nextInt();
			if(classMenu == 0) { //종료 체크
				break; //while문 종료
			}
			if (!studentClassDAO.isStudentclass(classMenu)) { //미신청 체크
				System.out.println("해당 과목 코드는 미신청입니다!");
				continue; //미신청이기 때문에 처음으로 돌아간다.
			}
			System.out.print("성적을 입력하세요. >>>");
			int score = input.nextInt();
			if(score < 0 || score > 100) { //성적이 0 - 100 까지인지 체크
				System.out.println("Error: 성적은 0부터 100사이의 숫자만 입력해 주세요.");
				continue;
			}
			//정상적인 과목과 성적이 입력된 경우
			if (studentClassDAO.insertScore(score, studentNumber, classMenu)) {
				System.out.println("성적 입력이 완료 되었습니다." + "\n");
			}
			else {
				System.out.println("성적입력에 실패했습니다." + "\n");
			}			 		
		}
	}

	//5. 학생 정보 조회 메뉴
	public void informStudent() {
		System.out.println("메뉴를 선택 해주세요. 1. 특정학생만 / 2. 전체 학생");
		int menu = input.nextInt();
		switch(menu) {
		case 1: //특정 학생의 성적 정보
			printOne();
			break;
		case 2: //전체 학생의 성적 정보
			printStudentInfo();
			break;
		}	
	}
	
	private void printOne() {
		System.out.print("학생의 학번을 입력 하세요");
		int studentNumber = input.nextInt(); //학번 입력받음
		
		ArrayList<Student> list = studentDAO.All();
		ArrayList<Student> searchList = new ArrayList<>();
		for (Student student : list) {
			if (student.getStudentNumber() == studentNumber) {
				searchList.add(student);
			}
		}
		if (searchList.size() < 1) {
			System.out.println("검색된 학생이 없습니다.");
			return;
		}
		else {
			System.out.println("검색한 학생의 정보는 아래와 같습니다.");
			System.out.println("학번\t이름\t전화번호\t메모");
			for (Student student : searchList) {
				System.out.println(student);
			}
		}
}
	
	private void printStudentInfo() {
		ArrayList<Student> list = studentDAO.All();
		for (Student student : list) {
			System.out.println("학번 : " + student.getStudentNumber());
			System.out.println("이름 : " + student.getName());
			System.out.println("전화번호 : " + student.getPhoneNumber());
			System.out.println("메모 : " + student.getMemo());
			
			ArrayList<StudentClass> classes = studentClassDAO.AllStudentClass();
			for(StudentClass studentClass : classes) {
				if(student.getStudentNumber() == studentClass.getStudentNumber()) {
				System.out.println("수강중인 과목 : " + studentClass.getSubject() + ", 성적 : " + studentClass.getScore());
				}
			}
			System.out.println();
			}
		}
	}
