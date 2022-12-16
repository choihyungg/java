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

	
	public void insertBook() { //책 등록
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
		
		list.add(book);
	}
	
	public void rent() { //책 대여
		System.out.println("대여할 책의 코드를 입력해 주세요");
		System.out.print("코드: ");
		int code = stdIn.nextInt();
		
		System.out.print("대여 권수: ");
		int num = stdIn.nextInt();
		
		for (Book book : list) {
			if(code == book.getCode()) {
				if (book.getStock() > num) {
				book.setStock(book.getStock() - num);
				System.out.println(num + "권이 대여되었습니다.");
				return;
				}
				else {
					System.out.println("재고가 " + book.getStock() + "권 이여서 대여할 수 없습니다." );
					return;
				}
			}
			else {
				System.out.println("해당 코드의 책이 존재하지 않습니다.");
			}
		}
	}
	
	public void returnBook() {
		System.out.println("반납할 책의 코드를 입력해 주세요");
		System.out.print("코드: ");
		int code = stdIn.nextInt();
		
		System.out.print("반납 권수: ");
		int num = stdIn.nextInt();
		
		for (Book book : list) {
			if(code == book.getCode()) {
				book.setStock(book.getStock() + num);
				System.out.println(num + "권이 반납되었습니다.");
				return;
			}
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
	
	public void searchBook() { //책 검색
		System.out.println("검색할 책의 제목을 입력해 주세요");
		System.out.print("제목: ");
		String name = stdIn.next();
		
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
		for (Book book : list) {
			System.out.println(book);
		}
	}
}
