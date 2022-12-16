package school_DB;

import java.sql.SQLException;
import java.util.Scanner;

public class SchoolManageView {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("학생 관리 프로그램을 시작합니다.");
		StudentManage studentManage = new StudentManage();
		
		while (true) {
			System.out.println("메뉴를 선택하세요 >> 1. 학생 추가 / 2. 학생 삭제 / 3. 수강 신청 / "
					+ "4. 성적 입력 / 5. 정보 조회 / 6. 수강과목 관리 / 7. 프로그램 종료");
			int menu = input.nextInt();
			switch(menu) {
			case 1:
				System.out.println("학생 추가 및 수정 메뉴 입니다.");
				studentManage.addStudent();
				break;
			case 2:
				System.out.println("학생 삭제 메뉴 입니다.");
				studentManage.removeStudent();
				break;
			case 3:
				System.out.println("수강 신청 메뉴 입니다.");
				studentManage.addClass();
				break;
			case 4:
				System.out.println("성적 입력 메뉴 입니다.");
				studentManage.setScore();
				break;
			case 5:
				System.out.println("정보 조회 메뉴 입니다.");
				studentManage.informStudent();
				break;
			case 6:
				System.out.println("수강 과목 관리 메뉴 입니다.");
				studentManage.classInfo();
				break;
			case 7:
				System.out.println("프로그램을 종료 합니다.");
				return;
			default:
				System.out.println("없는 메뉴를 선택하였습니다.");
			}
		}

	}

}
