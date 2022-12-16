package book;

import java.util.ArrayList;
import java.util.Scanner;


public class BookManeger {
	private ArrayList<Book> list;
	private Scanner stdIn;
	
	public BookManeger() {
		list = new ArrayList<Book>();
		stdIn = new Scanner(System.in);
	}

	
	public void insertBook() { //å ���
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
		
		list.add(book);
	}
	
	public void rent() { //å �뿩
		System.out.println("�뿩�� å�� �ڵ带 �Է��� �ּ���");
		System.out.print("�ڵ�: ");
		int code = stdIn.nextInt();
		
		System.out.print("�뿩 �Ǽ�: ");
		int num = stdIn.nextInt();
		
		for (Book book : list) {
			if(code == book.getCode()) {
				if (book.getStock() > num) {
				book.setStock(book.getStock() - num);
				System.out.println(num + "���� �뿩�Ǿ����ϴ�.");
				return;
				}
				else {
					System.out.println("��� " + book.getStock() + "�� �̿��� �뿩�� �� �����ϴ�." );
					return;
				}
			}
			else {
				System.out.println("�ش� �ڵ��� å�� �������� �ʽ��ϴ�.");
			}
		}
	}
	
	public void returnBook() {
		System.out.println("�ݳ��� å�� �ڵ带 �Է��� �ּ���");
		System.out.print("�ڵ�: ");
		int code = stdIn.nextInt();
		
		System.out.print("�ݳ� �Ǽ�: ");
		int num = stdIn.nextInt();
		
		for (Book book : list) {
			if(code == book.getCode()) {
				book.setStock(book.getStock() + num);
				System.out.println(num + "���� �ݳ��Ǿ����ϴ�.");
				return;
			}
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
	
	public void searchBook() { //å �˻�
		System.out.println("�˻��� å�� ������ �Է��� �ּ���");
		System.out.print("����: ");
		String name = stdIn.next();
		
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
		for (Book book : list) {
			System.out.println(book);
		}
	}
}
