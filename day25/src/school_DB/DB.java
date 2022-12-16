package school_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	public Connection connection = null;
	
	DB() {
		getConnection();
	}
	
	//��� ���� ����
	private void getConnection() { //��� ����
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
	
	public void disConnect() { //���� ����. ���� ����ÿ� ���.
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
