package school_DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClassInfoDAO extends DB {
	
	//���� �ߺ�
	public boolean isClass(int code) {
		int res = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS cnt FROM classinfo WHERE code = '" + code + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();
			res = resultSet.getInt("cnt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res != 0? true : false;
	}
	
	//���� �߰�
	public boolean insertClass(Classinfo classinfo) {
		Statement statement = null;
		
		if (isClass(classinfo.getCode())) {
			System.out.println(classinfo.getCode() + " �ش� ���� �ڵ尡 �̹� �����մϴ�.");
			return false;
		}
		
		boolean res = false;
		int upd = 0;
		try {
			String sql = String.format("INSERT INTO classinfo VALUES (%d, '%s')", 
					classinfo.getCode(), classinfo.getSubject());
			statement = connection.createStatement();
			upd = statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		res = (upd == 0) ? false : true;
		return res;
	}
	
	//���� ����
	public void removeClass(int code) throws SQLException {
		PreparedStatement preparedStatement = null;
		StudentDAO studentDAO = new StudentDAO();
		
		String deleteSQL = " DELETE FROM classinfo WHERE code='" + code + "' ";
		
		preparedStatement = studentDAO.connection.prepareStatement(deleteSQL); // ���� ���� �غ�
		int cnt = preparedStatement.executeUpdate(); //������ ���� ������ ���� ��ȯ
		System.out.println("���� ������ �Ϸ�Ǿ����ϴ�.");
	}
	
	//���� ��ü
	public ArrayList<Classinfo> AllClass() {
		Statement statement = null;
		ArrayList<Classinfo> classes = new ArrayList<>();
		try {
			String sql = "SELECT * FROM classinfo ";
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Classinfo classinfo = new Classinfo(resultSet.getInt("code"), resultSet.getString("subject"));
				classes.add(classinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { 
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return classes;
	}
}
