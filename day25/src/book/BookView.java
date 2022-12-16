package book;

import java.util.Scanner;

public class BookView {
	public static void main(String[] args) {
	BookManeger book = new BookManeger();
	Scanner stdIn = new Scanner(System.in);
	
	while (true) {
		printMenu();
		System.out.print("����:");
		int choice = stdIn.nextInt();
		switch(choice) {
		case 1:
			book.insertBook();
			break;
		case 2:
			System.out.println("�ڵ�\t����\t�۰�\t������");
			book.searchBook();
			break;
		case 3:
			book.rent();
			break;
		case 4:
			book.returnBook();
			break;
		case 5:
			System.out.println("�ڵ�\t����\t�۰�\t������");
			book.printBook();
			break;
		case 6:
			System.out.println("�����մϴ�.");
			stdIn.close();
			return;
		default:
			System.out.println("�߸������̽��ϴ�.\n�ٽü������ּ���");
			break;
		}
	}
}


	private static void printMenu() {
		System.out.println("1. å ���");
		System.out.println("2. å �˻�");
		System.out.println("3. å �뿩");
		System.out.println("4. å �ݳ�");
		System.out.println("5. å ��ü���");
		System.out.println("6. ����");
		System.out.println();
	}
}
