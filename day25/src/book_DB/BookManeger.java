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
			System.out.println(book.getCode() + " �ش� �ڵ尡 �̹� �����մϴ�.");
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
	
	public void makeBook() { //å ���
		Book book = new Book();
		
		System.out.println("����� å�� ������ �Է��� �ּ���");
		
		System.out.print("�ڵ�: ");
		book.setCode(stdIn.nextInt());
		
		System.out.print("����: ");
		book.setName(stdIn.next());
		
		System.out.print("�۰�: ");
		book.setWriter(stdIn.next());
		
		System.out.print("��� ����: ");
		book.setStock(stdIn.nextInt());
		
//		list.add(book);
		
		if (insertBook(book)) {
			System.out.println("å�� ��ϵǾ����ϴ�.");
		}
		else {
			System.out.println("å ��Ͽ� �����߽��ϴ�.");
		}
	}
	
	private boolean updateBook(int code, int num, boolean flag) {
		//�뿩 : false , �ݳ� : true
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
	
	public void rent() { //å �뿩
		System.out.println("�뿩�� å�� �ڵ带 �Է��� �ּ���");
		System.out.print("�ڵ�: ");
		int code = stdIn.nextInt();
		
		System.out.print("�뿩 �Ǽ�: ");
		int num = stdIn.nextInt();
		
//		for (Book book : list) {
//			if(code == book.getCode()) {
//				if (book.getStock() > num) {
//					updateBook(code, num, false);
//				System.out.println(num + "���� �뿩�Ǿ����ϴ�.");
//				return;
//				}
//				else {
//					System.out.println("��� " + book.getStock() + "�� �̿��� �뿩�� �� �����ϴ�." );
//					return;
//				}
//			}
//			else {
//				System.out.println("�ش� �ڵ��� å�� �������� �ʽ��ϴ�.");
//			}
//		}
		
		if (bookDAO.isBook(code)) {
			Book book = selectOne(code);
			if (book.getStock() < num) {
				System.out.println("��� " + book.getStock() + "�� �̿��� �뿩�� �� �����ϴ�." );
		}
		else {
			updateBook(code, num, false);
			System.out.println(num + "���� �뿩�Ǿ����ϴ�.");
			}
			return;
		}
		else {
			System.out.println("�ش� �ڵ��� å�� �������� �ʽ��ϴ�.");
		}
	}
	
	public void returnBook() {
		System.out.println("�ݳ��� å�� �ڵ带 �Է��� �ּ���");
		System.out.print("�ڵ�: ");
		int code = stdIn.nextInt();
		
		System.out.print("�ݳ� �Ǽ�: ");
		int num = stdIn.nextInt();
		
//		for (Book book : list) {
//			if(code == book.getCode()) {
//				updateBook(code, num, true);
//				System.out.println(num + "���� �ݳ��Ǿ����ϴ�.");
//				return;
//			}
//			System.out.println("�ش� �ڵ��� å�� �������� �ʽ��ϴ�.");
//		}
		
		
		if (bookDAO.isBook(code)) {
			updateBook(code, num, true);
			System.out.println(num + "���� �ݳ��Ǿ����ϴ�.");
		}
		else {
			System.out.println("�ش� �ڵ��� å�� �������� �ʽ��ϴ�.");
		}
	}
	
//	public void searchBook() {
//		System.out.println("�˻��� å�� ������ �Է��� �ּ���");
//		System.out.print("����: ");
//		String name = stdIn.next();
//		
//		for (Book book : list) {
//			if (name.equals(book.getName())) {
//				System.out.println("�˻��� å�� ������ �Ʒ��� �����ϴ�.");
//				System.out.println(book.getCode() + " / " + book.getName() + " / " + book.getWriter() + " / " + book.getStock() + "��");
//				return;
//			}
//			System.out.println("�ش� ������ å�� �������� �ʽ��ϴ�.");
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
	
	public void searchBook() { //å �˻�
		System.out.println("�˻��� å�� ������ �Է��� �ּ���");
		System.out.print("����: ");
		String name = stdIn.next();
		
//		ArrayList<Book> searchList = new ArrayList<>();
//		for (Book book : list) {
//			if (book.getName().contains(name)) {
//				searchList.add(book);
//			}
//		}
//		if (searchList.size() < 1) {
//			System.out.println("�˻��� å�� �����ϴ�.");
//		}
//		else {
//			System.out.println("�˻��� å�� ������ �Ʒ��� �����ϴ�.");
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
			System.out.println("�˻��� å�� �����ϴ�.");
		}
		else {
			System.out.println("�˻��� å�� ������ �Ʒ��� �����ϴ�.");
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
	
	//��� ���� ����
	private void getConnection() { //��� ����
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
	
	public void disConnect() { //���� ����. ���� ����ÿ� ���.
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
