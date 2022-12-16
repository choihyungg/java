package school_DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class StudentClassDAO extends DB {
	
	//�ߺ� ��ȸ
	public boolean isStudentclass(int code) {
		int res = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS cnt FROM studentclass WHERE code = '" + code + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();
			res = resultSet.getInt("cnt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res != 0? true : false;
	}
	
	//���� ���
	public boolean insertStudentclass(int studentNumber, int code, String subject, int score) {
		Statement statement = null;
		StudentDAO studentDAO = new StudentDAO();
		
		if (isStudentclass(code) && studentDAO.isStudent(studentNumber)) {
			System.out.println("�ش� ������ �̹� �����մϴ�.");
			return false;
		}
		
		boolean res = false;
		int upd = 0;
		try {
			String sql = String.format("INSERT INTO studentclass VALUES (%d, %d, '%s', %d)", 
					studentNumber, code, subject, score);
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
	
	//�������
	public void cancelClass(int code) throws SQLException {
		PreparedStatement preparedStatement = null;		
		
		String deleteSQL = " DELETE FROM studentclass WHERE code='" + code + "' ";
		
		preparedStatement = connection.prepareStatement(deleteSQL); // ���� ���� �غ�
		int cnt = preparedStatement.executeUpdate(); //������ ���� ������ ���� ��ȯ
		System.out.println("���� ���Ⱑ �Ϸ�Ǿ����ϴ�.");
	}
	
	//���� ���
	public boolean insertScore(int score, int studentNumber, int code) throws SQLException {
		PreparedStatement preparedStatement = null;
		
		String updateSQL = String.format("UPDATE studentclass SET score = %d WHERE (studentNumber = %d) and (code = %d)", 
				score, studentNumber, code);
		
		preparedStatement = connection.prepareStatement(updateSQL); //���� ���� �غ�

		int cnt = preparedStatement.executeUpdate(); //������ ���� ������ ���� ��ȯ
		return true;
	}
	
	//��û�� ���� ��ü
	public ArrayList<StudentClass> AllStudentClass() {
		Statement statement = null;
		ArrayList<StudentClass> classes = new ArrayList<>();
		try {
			String sql = "SELECT * FROM studentclass ";
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				StudentClass studentClass = new StudentClass(resultSet.getInt("studentNumber"), resultSet.getInt("code"), resultSet.getString("subject"), resultSet.getInt("score"));
				classes.add(studentClass);
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
