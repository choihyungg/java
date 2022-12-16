package school_DB;

import java.sql.SQLException;
import java.util.Scanner;

public class SchoolManageView {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("�л� ���� ���α׷��� �����մϴ�.");
		StudentManage studentManage = new StudentManage();
		
		while (true) {
			System.out.println("�޴��� �����ϼ��� >> 1. �л� �߰� / 2. �л� ���� / 3. ���� ��û / "
					+ "4. ���� �Է� / 5. ���� ��ȸ / 6. �������� ���� / 7. ���α׷� ����");
			int menu = input.nextInt();
			switch(menu) {
			case 1:
				System.out.println("�л� �߰� �� ���� �޴� �Դϴ�.");
				studentManage.addStudent();
				break;
			case 2:
				System.out.println("�л� ���� �޴� �Դϴ�.");
				studentManage.removeStudent();
				break;
			case 3:
				System.out.println("���� ��û �޴� �Դϴ�.");
				studentManage.addClass();
				break;
			case 4:
				System.out.println("���� �Է� �޴� �Դϴ�.");
				studentManage.setScore();
				break;
			case 5:
				System.out.println("���� ��ȸ �޴� �Դϴ�.");
				studentManage.informStudent();
				break;
			case 6:
				System.out.println("���� ���� ���� �޴� �Դϴ�.");
				studentManage.classInfo();
				break;
			case 7:
				System.out.println("���α׷��� ���� �մϴ�.");
				return;
			default:
				System.out.println("���� �޴��� �����Ͽ����ϴ�.");
			}
		}

	}

}
