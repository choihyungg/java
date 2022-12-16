package school_DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO extends DB {
	
	//�л� �ߺ� ��ȸ
	public boolean isStudent(int studentNumber) {
		int res = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS cnt FROM studentinfo WHERE studentNumber = '" + studentNumber + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();
			res = resultSet.getInt("cnt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res != 0? true : false;
	}
	
	//�л� �߰�
	public boolean insertStuent(Student student) {
		
		Statement statement = null;
		if (isStudent(student.getStudentNumber())) {
			System.out.println(student.getStudentNumber() + " �ش� �й��� �̹� �����մϴ�.");
			return false;
		}
		
		boolean res = false;
		int upd = 0;
		try {
			String sql = String.format("INSERT INTO studentinfo VALUES (%d, '%s', '%s', '%s')", 
					student.getStudentNumber(), student.getName(), student.getPhoneNumber(), student.getMemo());
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
	
	
	//�л� ����
	public void removeStudent(int studentNumber) throws SQLException {
		PreparedStatement preparedStatement = null;
		StudentDAO studentDAO = new StudentDAO();

		
		String deleteSQL = " DELETE FROM studentinfo WHERE studentNumber='" + studentNumber + "' ";
		
		preparedStatement = studentDAO.connection.prepareStatement(deleteSQL); // ���� ���� �غ�
		int cnt = preparedStatement.executeUpdate(); //������ ���� ������ ���� ��ȯ
		System.out.println("�л� ������ �Ϸ�Ǿ����ϴ�.");
	}
	
	//�л� ��ü
	public ArrayList<Student> All() {
		Statement statement = null;
		ArrayList<Student> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM studentinfo ";
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Student student = new Student(resultSet.getInt("StudentNumber"), resultSet.getString("name"),
						resultSet.getString("phoneNumber"), resultSet.getString("memo"));
				list.add(student);
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
		return list;
	}
	
	//Ư�� �л�
	public Student selectOne(int studentNumber) {
		Statement statement = null;
		Student student = null;
		try {
			String sql = "SELECT * FROM studentinfo WHERE studentNumber = '" + studentNumber + "'";
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				student = new Student();
				student.setStudentNumber(resultSet.getInt("studentNumber"));
				student.setName(resultSet.getString("name"));
				student.setPhoneNumber(resultSet.getString("phoneNumber"));
				student.setMemo(resultSet.getString("memo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return student;
}
}
