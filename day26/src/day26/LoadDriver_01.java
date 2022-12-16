package day26;

/*
JDBC�� �̿��ϴ� ���α׷� �ۼ� ����.
1. ����ϰ��� �ϴ� RDBMS���� �����ϴ� JDBC ����̹��� ��ġ.
2. JDBC ����̹��� �ε�.
����̹��� �ε��� ������ Ŭ������ ���� ���ε��� �����ϴ� Class Ŭ������ static �޼����� forName() �޼��带 ���.
3. DBMS�� ������ ����.
java.sql ��Ű���� �����ϴ� Connection ��ü�� ���.
4. SQL�� ����.
select ������ ������ ������ java.sql ��Ű���� �����ϴ� Statement ��ü�� ResultSet ��ü�� ���.
Statement ��ü�� SQL ������ ���� ���̰� ResultSet�� select ������ ������ ����� �ٷ�� ���� ��ü.
 */

public class LoadDriver_01 {
	//����̹� Ŭ������ �ε�
	public static void LoadDriver() {
		try {
			Class.forName("org.mariadb.jdbc.Driver"); //org.mariadb.jdbc.Driver Ŭ������ �޸𸮿� �ε�.
			System.out.println("Driver Load Success!");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoadDriver();
	}
}
