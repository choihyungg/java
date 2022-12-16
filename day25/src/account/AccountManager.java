package account;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

class AccountDAO {
	public Connection connection = null;
	
	AccountDAO() {
		getConnection();
	}
	
	//디비 관련 시작
	private void getConnection() { //디비 연결
		try {
			String url = "jdbc:mariadb://localhost:3308/sample_java_account";
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
	
	public boolean isAccount(int id) { //동일한 계좌가 있는지
		int res = 0;
		
		try {
			String sql = "SELECT COUNT(*) AS cnt FROM account WHERE id = '" + id + "'";
//			System.out.println(sql);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();
			res = resultSet.getInt("cnt");
//			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res != 0? true : false;
	}
}

public class AccountManager {
	private ArrayList<Account> list;
	private Scanner stdIn;
	private AccountDAO accountDAO;

	
	public AccountManager() {
		accountDAO = new AccountDAO();
		list = new ArrayList<Account>();
		stdIn = new Scanner(System.in);
	}
	


	private boolean insertAccount(Account account) {
		//계좌 생성.
		//계좌 생성 전에 동일한 계좌 번호가 존재하는지 확인
		Statement statement = null;
		if (accountDAO.isAccount(account.getId())) {
			System.out.println(account.getId() + " 계좌가 존재합니다.");
			return false;
		}
		
		boolean res = false;
		int upd = 0;
		try {
			String sql = String.format("INSERT INTO account VALUES (%d, '%s', %d)", 
					account.getId(), account.getName(), account.getBalance());
//			System.out.println(sql);
			statement = accountDAO.connection.createStatement();
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
	
	private Account selectOne(int id) {
		//계좌 번호를 받아 정보 전달
		Statement statement = null;
		Account account = null;
		try {
			String sql = "SELECT * FROM account WHERE id = '" + id + "'";
			System.out.println(sql);
			statement = accountDAO.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				account = new Account();
				account.setId(resultSet.getInt("id"));
				account.setName(resultSet.getString("name"));
				account.setBalance(resultSet.getInt("balance"));
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
		return account;
	}
	
	private ArrayList<Account> selectAll() {
		//전체 정보 전달
		Statement statement = null;
		ArrayList<Account> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM account ";
//			System.out.println(sql);
			statement = accountDAO.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Account account = new Account(resultSet.getInt("id"), resultSet.getString("name"),
						 resultSet.getInt("balance"));
				list.add(account);
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

	public void makeAccount() { //계좌 개설
		Account account = new Account();
		
		System.out.print("계좌 번호: ");
		account.setId(stdIn.nextInt());
//		int getAccount = stdIn.nextInt();
//		
//		for (Account account2 : list) {
//		if(getAccount == account2.getId()) {
//			System.out.println("계좌 번호가 이미 존재합니다.");
//			return;
//			}
//
//		}
//		account.setId(getAccount);
//		list.add(account);

		System.out.print("이름: ");
		account.setName(stdIn.next());
		
		System.out.print("입금액: ");
		account.setBalance(stdIn.nextLong());
		
		if (insertAccount(account)) {
			System.out.println("계좌가 개설되었습니다.");
//			System.out.println(list.toString() + "/t");
		}
		else {
			System.out.println("계좌 생성에 실패했습니다.");
		}
		
//		System.out.println("계좌가 개설되었습니다.");
//		System.out.println(list.toString() + "\t");
		

	}

	public void deposit() { //입금
		System.out.print("계좌번호: ");
		int id = stdIn.nextInt();
		
		System.out.print("입금액: ");
		long money = stdIn.nextLong();
		
		//해당 계좌 찾기
		if (accountDAO.isAccount(id)) {
			updateBalance(id, money, true);
			System.out.println("입금완료 되었습니다.");
		}
		else {
			System.out.println("해당 계좌번호가 존재하지 않습니다.");
		}
	}

	public void withdraw() { //출금
		System.out.print("계좌번호: ");
		int id = stdIn.nextInt();
		
		System.out.print("출금액: ");
		long money = stdIn.nextLong();
		
		//해당 계좌 찾기
		if (accountDAO.isAccount(id)) {
			Account account = selectOne(id);
			if (account.getBalance() < money) {
				System.out.println("잔액이 부족합니다.");
		}
		else {
			updateBalance(id, money, false);
			System.out.println("출금완료 되었습니다.");
			}
			return;
		}
		else {
		System.out.println("해당 계좌번호가 존재하지 않습니다.");
		}
	}
	
	private boolean updateBalance(int id, long money, boolean flag) {
		//입금, 출금
		//flag true : 입금 / false : 출금
		Statement statement = null;
		boolean res = false;
		int upd = 0;
		try {
			String sql;
			if (flag) {
				sql = String.format("UPDATE account SET balance = balance + %d WHERE (id = %d)", money, id);
			}
			else {
				sql = String.format("UPDATE account SET balance = balance - %d WHERE (id = %d)", money, id);
			}
			System.out.println(sql);
			statement = accountDAO.connection.createStatement();
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


	public void inquire() { //잔액 조회
		System.out.print("계좌번호: ");
		int id = stdIn.nextInt();
		
//		Account account = selectOne(id);
//		if (account != null) {
//			System.out.println(account.getId() + "\t" + account.getName() + "\t" + account.getBalance());
//			return;
//		}
//		System.out.println("해당 계좌번호가 존재하지 않습니다.");
		
		Account account = selectOne(id);
		if (account != null) {
			System.out.println(account.getId() + "\t" + account.getName() + "\t" + account.getBalance());
		}
		else {
		System.out.println("해당 계좌번호가 존재하지 않습니다.");
		}
	}
	
	

	public void display() { //출력
		ArrayList<Account> list = selectAll();
		for(Account account : list) {
			System.out.println(account.getId() + "\t" + account.getName() + "\t" + account.getBalance());
		}
	}
	
}

