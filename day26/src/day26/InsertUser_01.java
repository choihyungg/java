package day26;

import java.sql.SQLException;
import java.util.Scanner;

class User { //회원 데이터 정의. 모델 클래스
	public String userID;
	public String name;
	public int age;
	public String job;
}

//인서트
class DBInsert extends DB {
	private User setUser() {
		//콘솔로 입력을 받음
		User user = new User();
		Scanner scanner = new Scanner(System.in);
		
		boolean validate = false;
		String validateTemp;
		do {
			System.out.println("신규 회원의 아이디를 입력하세요(영어나 숫자, 이전 데이터에서 사용한 값은 안됨): ");
			user.userID = scanner.next();
			System.out.println("신규 회원의 이름을 입력하세요: ");
			user.name = scanner.next();
			System.out.println("신규 회원의 나이를 입력하세요: ");
			user.age = Integer.parseInt(scanner.next());
			System.out.println("신규 회원의 직업을 입력하세요: ");
			user.job = scanner.next();
			
			System.out.println("신규 회원 : " + user.userID + " / " + user.name
					+ " / " + user.age + " / " + user.job + "이 맞습니까? (y/n)");
			validateTemp = scanner.next(); //입력값 저장
			
			validate = !validateTemp.equals("y"); //입력 값이 y가 아닌 경우 true, 같은 경우 false 반환
//			System.out.println(validate);
		} while (validate);
		
		scanner.close();
		return user;
	}


public void insertUser() throws SQLException {
	//setUser() 호출 후 정보를 받아서 디비에 입력
	User user = setUser();
	//매개변수화된 SQL문 작성
	String insertSql = " INSERT INTO tUser (userID, name, age, job ) VALUES (?, ?, ?, ?) ";
	
	//preparedStatement 얻기 및 값 지정
	preparedStatement = conn.prepareStatement(insertSql); //쿼리 실행 준비
	// ?에 들어갈 값을 지정해주는데, ?는 순서에 따라 1번부터 번호가 부여.
	preparedStatement.setString(1, user.userID);
	preparedStatement.setString(2, user.name);
	preparedStatement.setInt(3, user.age);
	preparedStatement.setString(4, user.job);
	
	//SQL문 실행
	int rows = preparedStatement.executeUpdate(); //executeUpdate()을 호출하면 sql문이 실행. 영향을 받은 데이터 갯수(정수) 반환.
//	pstmt.excuteQuery() : select
//	pstmt.excuteUpdate() : insert, update, delete ..
	
	//insert의 경우 정상적으로 실행이 되었으면 1을 리턴.
	if (rows == 1) {
		System.out.println("데이터 입력에 성공했습니다.");
	}
	else {
		System.out.println("데이터 입력에 실패했습니다.");
	}
}
}
public class InsertUser_01 {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBInsert myDB = new DBInsert();
		myDB.connectDB();
		myDB.insertUser();
		myDB.closeDB();
	}
}
