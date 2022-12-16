package school_DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class StudentClassDAO extends DB {
	
	//중복 조회
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
	
	//과목 등록
	public boolean insertStudentclass(int studentNumber, int code, String subject, int score) {
		Statement statement = null;
		StudentDAO studentDAO = new StudentDAO();
		
		if (isStudentclass(code) && studentDAO.isStudent(studentNumber)) {
			System.out.println("해당 과목이 이미 존재합니다.");
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
	
	//수강취소
	public void cancelClass(int code) throws SQLException {
		PreparedStatement preparedStatement = null;		
		
		String deleteSQL = " DELETE FROM studentclass WHERE code='" + code + "' ";
		
		preparedStatement = connection.prepareStatement(deleteSQL); // 쿼리 실행 준비
		int cnt = preparedStatement.executeUpdate(); //영향을 받은 데이터 갯수 반환
		System.out.println("수강 포기가 완료되었습니다.");
	}
	
	//성적 등록
	public boolean insertScore(int score, int studentNumber, int code) throws SQLException {
		PreparedStatement preparedStatement = null;
		
		String updateSQL = String.format("UPDATE studentclass SET score = %d WHERE (studentNumber = %d) and (code = %d)", 
				score, studentNumber, code);
		
		preparedStatement = connection.prepareStatement(updateSQL); //쿼리 실행 준비

		int cnt = preparedStatement.executeUpdate(); //영향을 받은 데이터 갯수 반환
		return true;
	}
	
	//신청한 과목 전체
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
