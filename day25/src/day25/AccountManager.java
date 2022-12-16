package day25;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
	private ArrayList<Account> list;
	private Scanner stdIn;
	
	private final String error = "�ش� ���¹�ȣ�� �������� �ʽ��ϴ�.";
	
	public AccountManager() {
		list = new ArrayList<Account>();
		stdIn = new Scanner(System.in);
	}

	public void makeAccount() { //���� ����
		Account account = new Account();
		
		System.out.print("���� ��ȣ: ");
		int getAccount = stdIn.nextInt();
		
		for (Account account2 : list) {
		if(getAccount == account2.getId()) {
			System.out.println("���� ��ȣ�� �̹� �����մϴ�.");
			return;
			}

		}
		account.setId(getAccount);
		list.add(account);

		System.out.print("�̸�: ");
		account.setName(stdIn.next());
		
		System.out.print("�Աݾ�: ");
		account.setBalance(stdIn.nextLong());
		
		System.out.println("���°� �����Ǿ����ϴ�.");
		System.out.println(list.toString() + "\t");
		

	}

	public void deposit() { //�Ա�
		System.out.print("���¹�ȣ: ");
		int id = stdIn.nextInt();
		
		System.out.print("�Աݾ�: ");
		long money = stdIn.nextLong();
		
		//�ش� ���� ã��
//		for (Account account : list) {
//			if (account.getId() == id) { //������ ���°� �ִٸ�
//				account.setBalance(money + account.getBalance());
//				System.out.println("�ԱݿϷ� �Ǿ����ϴ�.");
//				return;
//			}
//		}
//		System.out.println(error);
//	}
		
		Account account = findAccount(id);
		if (account != null) {
			account.setBalance(money + account.getBalance());
			System.out.println("�ԱݿϷ� �Ǿ����ϴ�.");
		}
	}

	public void withdraw() { //���
		System.out.print("���¹�ȣ: ");
		int id = stdIn.nextInt();
		
		System.out.print("��ݾ�: ");
		long money = stdIn.nextLong();
		
//		//�ش� ���� ã��
//		for (Account account : list) {
//			if (account.getId() == id) { //������ ���°� �ִٸ�
//				if (account.getBalance() < money) {
//					System.out.println("�ܾ��� �����մϴ�.");
//			}
//			else {
//				account.setBalance(account.getBalance() - money);
//				System.out.println("��ݿϷ� �Ǿ����ϴ�.");
//				}
//				return;
//			}
//		}
//		System.out.println(error);	
//	}
		
		Account account = findAccount(id);
		if (account != null) {
			if (account.getBalance() < money) {
				System.out.println("�ܾ��� �����մϴ�.");
		}
		else {
			account.setBalance(account.getBalance() - money);
			System.out.println("��ݿϷ� �Ǿ����ϴ�.");
			}
			return;
		}
		System.out.println(error);
	}
	


	public void inquire() { //�ܾ� ��ȸ
		System.out.print("���¹�ȣ: ");
		int id = stdIn.nextInt();
		
//		Account account = new Account();
		//�ش� ���� ã��
//		for (Account account : list) {
//			if (account.getId() == id) { //������ ���°� �ִٸ�
//				System.out.println(account);
//				return;
//			}
//		}
//		System.out.println(error);
		
		Account account = findAccount(id);
		if (account != null) {
			System.out.println(account);
		}
	}
	
	

	public void display() { //���
		for(Account account : list) {
			System.out.println(account);
		}
	}
	
	private Account findAccountTraverse(int id) {
		//�ش� ���� ã��
		for (Account account : list) {
			if(account.getId() == id) { //������ ���°� �ִٸ�
				return account;
			}
		}
		return null;
	}
	
	private Account findAccount(int id) { // �Ա�, ���, ��ȸ�ÿ� ���� ã��
		Account account = findAccountTraverse(id);
			if(account == null) {
				System.out.println(error);
			}
			return account;
			}

		private boolean isAccount(int id) { //���� �ߺ� ��ȸ�̱� ������ account�� �������� ���� bool ���� ����
			return (findAccountTraverse(id) == null) ? false : true;
	}
}

