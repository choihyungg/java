package school_DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClassInfoDAO extends DB {
	
	//과목 중복
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
	
	//과목 추가
	public boolean insertClass(Classinfo classinfo) {
		Statement statement = null;
		
		if (isClass(classinfo.getCode())) {
			System.out.println(classinfo.getCode() + " 해당 과목 코드가 이미 존재합니다.");
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
	
	//과목 삭제
	public void removeClass(int code) throws SQLException {
		PreparedStatement preparedStatement = null;
		StudentDAO studentDAO = new StudentDAO();
		
		String deleteSQL = " DELETE FROM classinfo WHERE code='" + code + "' ";
		
		preparedStatement = studentDAO.connection.prepareStatement(deleteSQL); // 쿼리 실행 준비
		int cnt = preparedStatement.executeUpdate(); //영향을 받은 데이터 갯수 반환
		System.out.println("과목 삭제가 완료되었습니다.");
	}
	
	//과목 전체
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
