package day25;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
	private ArrayList<Account> list;
	private Scanner stdIn;
	
	private final String error = "해당 계좌번호가 존재하지 않습니다.";
	
	public AccountManager() {
		list = new ArrayList<Account>();
		stdIn = new Scanner(System.in);
	}

	public void makeAccount() { //계좌 개설
		Account account = new Account();
		
		System.out.print("계좌 번호: ");
		int getAccount = stdIn.nextInt();
		
		for (Account account2 : list) {
		if(getAccount == account2.getId()) {
			System.out.println("계좌 번호가 이미 존재합니다.");
			return;
			}

		}
		account.setId(getAccount);
		list.add(account);

		System.out.print("이름: ");
		account.setName(stdIn.next());
		
		System.out.print("입금액: ");
		account.setBalance(stdIn.nextLong());
		
		System.out.println("계좌가 개설되었습니다.");
		System.out.println(list.toString() + "\t");
		

	}

	public void deposit() { //입금
		System.out.print("계좌번호: ");
		int id = stdIn.nextInt();
		
		System.out.print("입금액: ");
		long money = stdIn.nextLong();
		
		//해당 계좌 찾기
//		for (Account account : list) {
//			if (account.getId() == id) { //동일한 계좌가 있다면
//				account.setBalance(money + account.getBalance());
//				System.out.println("입금완료 되었습니다.");
//				return;
//			}
//		}
//		System.out.println(error);
//	}
		
		Account account = findAccount(id);
		if (account != null) {
			account.setBalance(money + account.getBalance());
			System.out.println("입금완료 되었습니다.");
		}
	}

	public void withdraw() { //출금
		System.out.print("계좌번호: ");
		int id = stdIn.nextInt();
		
		System.out.print("출금액: ");
		long money = stdIn.nextLong();
		
//		//해당 계좌 찾기
//		for (Account account : list) {
//			if (account.getId() == id) { //동일한 계좌가 있다면
//				if (account.getBalance() < money) {
//					System.out.println("잔액이 부족합니다.");
//			}
//			else {
//				account.setBalance(account.getBalance() - money);
//				System.out.println("출금완료 되었습니다.");
//				}
//				return;
//			}
//		}
//		System.out.println(error);	
//	}
		
		Account account = findAccount(id);
		if (account != null) {
			if (account.getBalance() < money) {
				System.out.println("잔액이 부족합니다.");
		}
		else {
			account.setBalance(account.getBalance() - money);
			System.out.println("출금완료 되었습니다.");
			}
			return;
		}
		System.out.println(error);
	}
	


	public void inquire() { //잔액 조회
		System.out.print("계좌번호: ");
		int id = stdIn.nextInt();
		
//		Account account = new Account();
		//해당 계좌 찾기
//		for (Account account : list) {
//			if (account.getId() == id) { //동일한 계좌가 있다면
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
	
	

	public void display() { //출력
		for(Account account : list) {
			System.out.println(account);
		}
	}
	
	private Account findAccountTraverse(int id) {
		//해당 계좌 찾기
		for (Account account : list) {
			if(account.getId() == id) { //동일한 계좌가 있다면
				return account;
			}
		}
		return null;
	}
	
	private Account findAccount(int id) { // 입금, 출금, 조회시에 계좌 찾기
		Account account = findAccountTraverse(id);
			if(account == null) {
				System.out.println(error);
			}
			return account;
			}

		private boolean isAccount(int id) { //계좌 중복 조회이기 때문에 account를 리턴하지 말고 bool 값을 리턴
			return (findAccountTraverse(id) == null) ? false : true;
	}
}

