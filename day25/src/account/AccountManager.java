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
	
	//��� ���� ����
	private void getConnection() { //��� ����
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
	
	public void disConnect() { //���� ����. ���� ����ÿ� ���.
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAccount(int id) { //������ ���°� �ִ���
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
		//���� ����.
		//���� ���� ���� ������ ���� ��ȣ�� �����ϴ��� Ȯ��
		Statement statement = null;
		if (accountDAO.isAccount(account.getId())) {
			System.out.println(account.getId() + " ���°� �����մϴ�.");
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
		//���� ��ȣ�� �޾� ���� ����
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
		//��ü ���� ����
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

	public void makeAccount() { //���� ����
		Account account = new Account();
		
		System.out.print("���� ��ȣ: ");
		account.setId(stdIn.nextInt());
//		int getAccount = stdIn.nextInt();
//		
//		for (Account account2 : list) {
//		if(getAccount == account2.getId()) {
//			System.out.println("���� ��ȣ�� �̹� �����մϴ�.");
//			return;
//			}
//
//		}
//		account.setId(getAccount);
//		list.add(account);

		System.out.print("�̸�: ");
		account.setName(stdIn.next());
		
		System.out.print("�Աݾ�: ");
		account.setBalance(stdIn.nextLong());
		
		if (insertAccount(account)) {
			System.out.println("���°� �����Ǿ����ϴ�.");
//			System.out.println(list.toString() + "/t");
		}
		else {
			System.out.println("���� ������ �����߽��ϴ�.");
		}
		
//		System.out.println("���°� �����Ǿ����ϴ�.");
//		System.out.println(list.toString() + "\t");
		

	}

	public void deposit() { //�Ա�
		System.out.print("���¹�ȣ: ");
		int id = stdIn.nextInt();
		
		System.out.print("�Աݾ�: ");
		long money = stdIn.nextLong();
		
		//�ش� ���� ã��
		if (accountDAO.isAccount(id)) {
			updateBalance(id, money, true);
			System.out.println("�ԱݿϷ� �Ǿ����ϴ�.");
		}
		else {
			System.out.println("�ش� ���¹�ȣ�� �������� �ʽ��ϴ�.");
		}
	}

	public void withdraw() { //���
		System.out.print("���¹�ȣ: ");
		int id = stdIn.nextInt();
		
		System.out.print("��ݾ�: ");
		long money = stdIn.nextLong();
		
		//�ش� ���� ã��
		if (accountDAO.isAccount(id)) {
			Account account = selectOne(id);
			if (account.getBalance() < money) {
				System.out.println("�ܾ��� �����մϴ�.");
		}
		else {
			updateBalance(id, money, false);
			System.out.println("��ݿϷ� �Ǿ����ϴ�.");
			}
			return;
		}
		else {
		System.out.println("�ش� ���¹�ȣ�� �������� �ʽ��ϴ�.");
		}
	}
	
	private boolean updateBalance(int id, long money, boolean flag) {
		//�Ա�, ���
		//flag true : �Ա� / false : ���
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


	public void inquire() { //�ܾ� ��ȸ
		System.out.print("���¹�ȣ: ");
		int id = stdIn.nextInt();
		
//		Account account = selectOne(id);
//		if (account != null) {
//			System.out.println(account.getId() + "\t" + account.getName() + "\t" + account.getBalance());
//			return;
//		}
//		System.out.println("�ش� ���¹�ȣ�� �������� �ʽ��ϴ�.");
		
		Account account = selectOne(id);
		if (account != null) {
			System.out.println(account.getId() + "\t" + account.getName() + "\t" + account.getBalance());
		}
		else {
		System.out.println("�ش� ���¹�ȣ�� �������� �ʽ��ϴ�.");
		}
	}
	
	

	public void display() { //���
		ArrayList<Account> list = selectAll();
		for(Account account : list) {
			System.out.println(account.getId() + "\t" + account.getName() + "\t" + account.getBalance());
		}
	}
	
}

