package book_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class BookManeger {
	private ArrayList<Book> list;
	private Scanner stdIn;
	private BookDAO bookDAO;
	
	public BookManeger() {
		bookDAO = new BookDAO();
		list = new ArrayList<Book>();
		stdIn = new Scanner(System.in);
	}

	private boolean insertBook(Book book) {
		Statement statement = null;
		if (bookDAO.isBook(book.getCode())) {
			System.out.println(book.getCode() + " 해당 코드가 이미 존재합니다.");
			return false;
		}
		
		boolean res = false;
		int upd = 0;
		try {
			String sql = String.format("INSERT INTO book VALUES (%d, '%s', '%s', %d)", 
					book.getCode(), book.getName(), book.getWriter(), book.getStock());
			statement = bookDAO.connection.createStatement();
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
	
	public void makeBook() { //책 등록
		Book book = new Book();
		
		System.out.println("등록할 책의 정보를 입력해 주세요");
		
		System.out.print("코드: ");
		book.setCode(stdIn.nextInt());
		
		System.out.print("제목: ");
		book.setName(stdIn.next());
		
		System.out.print("작가: ");
		book.setWriter(stdIn.next());
		
		System.out.print("재고 숫자: ");
		book.setStock(stdIn.nextInt());
		
//		list.add(book);
		
		if (insertBook(book)) {
			System.out.println("책이 등록되었습니다.");
		}
		else {
			System.out.println("책 등록에 실패했습니다.");
		}
	}
	
	private boolean updateBook(int code, int num, boolean flag) {
		//대여 : false , 반납 : true
		Statement statement = null;
		boolean res = false;
		int upd = 0;
		try {
			String sql;
			if (flag) {
				sql = String.format("UPDATE book SET stock = stock + %d WHERE (code = %d)", num, code);
			}
			else {
				sql = String.format("UPDATE book SET stock = stock - %d WHERE (code = %d)", num, code);
			}
			statement = bookDAO.connection.createStatement();
			upd = statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		res = (upd == 0) ? false : true;
		return res;
	}
	
	public void rent() { //책 대여
		System.out.println("대여할 책의 코드를 입력해 주세요");
		System.out.print("코드: ");
		int code = stdIn.nextInt();
		
		System.out.print("대여 권수: ");
		int num = stdIn.nextInt();
		
//		for (Book book : list) {
//			if(code == book.getCode()) {
//				if (book.getStock() > num) {
//					updateBook(code, num, false);
//				System.out.println(num + "권이 대여되었습니다.");
//				return;
//				}
//				else {
//					System.out.println("재고가 " + book.getStock() + "권 이여서 대여할 수 없습니다." );
//					return;
//				}
//			}
//			else {
//				System.out.println("해당 코드의 책이 존재하지 않습니다.");
//			}
//		}
		
		if (bookDAO.isBook(code)) {
			Book book = selectOne(code);
			if (book.getStock() < num) {
				System.out.println("재고가 " + book.getStock() + "권 이여서 대여할 수 없습니다." );
		}
		else {
			updateBook(code, num, false);
			System.out.println(num + "권이 대여되었습니다.");
			}
			return;
		}
		else {
			System.out.println("해당 코드의 책이 존재하지 않습니다.");
		}
	}
	
	public void returnBook() {
		System.out.println("반납할 책의 코드를 입력해 주세요");
		System.out.print("코드: ");
		int code = stdIn.nextInt();
		
		System.out.print("반납 권수: ");
		int num = stdIn.nextInt();
		
//		for (Book book : list) {
//			if(code == book.getCode()) {
//				updateBook(code, num, true);
//				System.out.println(num + "권이 반납되었습니다.");
//				return;
//			}
//			System.out.println("해당 코드의 책이 존재하지 않습니다.");
//		}
		
		
		if (bookDAO.isBook(code)) {
			updateBook(code, num, true);
			System.out.println(num + "권이 반납되었습니다.");
		}
		else {
			System.out.println("해당 코드의 책이 존재하지 않습니다.");
		}
	}
	
//	public void searchBook() {
//		System.out.println("검색할 책의 제목을 입력해 주세요");
//		System.out.print("제목: ");
//		String name = stdIn.next();
//		
//		for (Book book : list) {
//			if (name.equals(book.getName())) {
//				System.out.println("검색한 책의 정보는 아래와 같습니다.");
//				System.out.println(book.getCode() + " / " + book.getName() + " / " + book.getWriter() + " / " + book.getStock() + "권");
//				return;
//			}
//			System.out.println("해당 제목의 책이 존재하지 않습니다.");
//		}
//	}
	
	private Book searchOne(String name) {
		Statement statement = null;
		Book book = null;
		try {
			String sql = "SELECT * FROM book WHERE name = '" + name + "'";
			statement = bookDAO.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				book = new Book();
				book.setCode(resultSet.getInt("code"));
				book.setName(resultSet.getString("name"));
				book.setWriter(resultSet.getString("writer"));
				book.setStock(resultSet.getInt("stock"));
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
		return book;
	}
	
	public void searchBook() { //책 검색
		System.out.println("검색할 책의 제목을 입력해 주세요");
		System.out.print("제목: ");
		String name = stdIn.next();
		
//		ArrayList<Book> searchList = new ArrayList<>();
//		for (Book book : list) {
//			if (book.getName().contains(name)) {
//				searchList.add(book);
//			}
//		}
//		if (searchList.size() < 1) {
//			System.out.println("검색된 책이 없습니다.");
//		}
//		else {
//			System.out.println("검색한 책의 정보는 아래와 같습니다.");
//			for (Book book : searchList) {
//				System.out.println(book);
//			}
//		}
		
		ArrayList<Book> list = selectAll();
		ArrayList<Book> searchList = new ArrayList<>();
		for (Book book : list) {
			if (book.getName().contains(name)) {
				searchList.add(book);
			}
		}
		if (searchList.size() < 1) {
			System.out.println("검색된 책이 없습니다.");
		}
		else {
			System.out.println("검색한 책의 정보는 아래와 같습니다.");
			for (Book book : searchList) {
				System.out.println(book);
			}
		}
	}
	
	public void printBook() {
		ArrayList<Book> list = selectAll();
		for (Book book : list) {
			System.out.println(book);
		}
	}
	
	public ArrayList<Book> selectAll() {
		Statement statement = null;
		ArrayList<Book> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM book ";
			statement = bookDAO.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Book book = new Book(resultSet.getInt("code"), resultSet.getString("name"),
						resultSet.getString("writer"), resultSet.getInt("stock"));
				list.add(book);
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
	
	private Book selectOne(int code) {
		Statement statement = null;
		Book book = null;
		try {
			String sql = "SELECT * FROM book WHERE code = '" + code + "'";
			statement = bookDAO.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				book = new Book();
				book.setCode(resultSet.getInt("code"));
				book.setName(resultSet.getString("name"));
				book.setWriter(resultSet.getString("writer"));
				book.setStock(resultSet.getInt("stock"));
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
		return book;
	}
}


class BookDAO {
	public Connection connection = null;
	
	BookDAO() {
		getConnection();
	}
	
	//디비 관련 시작
	private void getConnection() { //디비 연결
		try {
			String url = "jdbc:mariadb://localhost:3308/book";
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
	
	public boolean isBook(int code) {
		int res = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS cnt FROM book WHERE code = '" + code + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();
			res = resultSet.getInt("cnt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res != 0? true : false;
	}
}
