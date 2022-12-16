package day26;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//select

class DBSelect extends DB {
	Scanner scanner = new Scanner(System.in);
	
	//추가된 코드
	protected ResultSet querySelect(String sql) {
		//select 실행 후 결과 반환
		try {
			preparedStatement = conn.prepareStatement(sql); //쿼리 실행 준비
			
			//SQL문이 INSERT, UPDATE, DELETE일 경우에는 executeUpdate()메소드를 호출하지만,
			//데이터를 가져오는 SELECT문일 경우에는 executeQuery() 메소드를 호출.
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
		//ResultSet은 SELECT문에 기술된 컬럼으로 구성된 행row의 집합.
		return resultSet;
	}
	
	private void printUser(String sql) throws SQLException {
		//select 실행 결과를 받아서 콘솔에 출력.
		//전체 목록 출력과 한 열만 출력하는 기능
		int totalRow;
		int num = 1;
		ResultSet resultSet = querySelect(sql);
		
		//ResultSet의 개수를 구해서 1일 경우와 1이 아닐 경우를 분리.
		//ResutlSet의 개수를 구하는 메서드는 없어서 다른 방법으로 개수를 구함.
		resultSet.last(); //마지막 row로 이동
		totalRow = resultSet.getRow(); //row count를 추출.
		resultSet.beforeFirst(); //처음 row로 커서를 이동.(초기 상태로 돌려놓음)
		if (totalRow == 1) {
			//데이터가 하나
			if (resultSet.next()) {
				System.out.println("아이디 : " + resultSet.getString(1));
				System.out.println("이름 : " + resultSet.getString(2));
				System.out.println("나이 : " + resultSet.getString(3));
				System.out.println("직업 : " + resultSet.getString(4));
				System.out.println();
			}
			else {
				System.out.println("입력하신 아이디의 데이터는 존재하지 않습니다.");
			}
		}
		else {
			//전체 목록 출력
			//데이터의 처음부터 읽어오므로 next를 만나면 한 줄을 읽어오고, 다음 줄로 커서가 이동.
			//한줄 한줄 내려가면서 데이터를 가져오고, 데이터가 끝가지 가면 종료.
			while (resultSet.next()) {
				System.out.println(num + "번째 회원");
				System.out.println("아이디 : " + resultSet.getString(1));
				System.out.println("이름 : " + resultSet.getString(2));
				System.out.println("나이 : " + resultSet.getString(3));
				System.out.println("직업 : " + resultSet.getString(4));
				System.out.println();
				num++;
			}
		}	
	}
	public void getAllUser() throws SQLException {
		//전체 출력
		String sql;
		System.out.println("전체 회원 목록입니다.");
		sql = "SELECT * FROM tUser ";
		printUser(sql);
		System.out.println("=============");
	}
	public void getOneUser() throws SQLException {
		//조건 출력
		String sql;
		String userID;
		String validateTemp;
		boolean validate = false;
		
		do {
			System.out.println("정보 검색 할 회원의 아이디를 입력하세요: ");
			userID = scanner.next();
			
			System.out.println("검색할 회원의 아이디가 " + userID + "이 맞습니까? (y/n)");
			validateTemp = scanner.next();
			
			//입력 값이 y가 아닌 경우 true, 같은 경우 false를 반환
			validate = !validateTemp.equals("y");
		} while (validate);
		sql = "SELECT * FROM tUser WHERE userID = '" + userID + "'";
		printUser(sql);
	}
}

public class SelectUser_01 {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBSelect myDB = new DBSelect();
		myDB.connectDB();
		myDB.getAllUser();
		myDB.getOneUser();
		myDB.closeDB();

	}

}
