package school_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	public Connection connection = null;
	
	DB() {
		getConnection();
	}
	
	//디비 관련 시작
	private void getConnection() { //디비 연결
		try {
			String url = "jdbc:mariadb://localhost:3308/school";
			String user = "root";
			String password = "8229";
			
			try {
				Class.forName("org.mariadb.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			this.connection = DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disConnect() { //연결 해제. 서비스 종료시에 사용.
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
