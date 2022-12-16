package book;

import java.util.Scanner;

public class BookView {
	public static void main(String[] args) {
	BookManeger book = new BookManeger();
	Scanner stdIn = new Scanner(System.in);
	
	while (true) {
		printMenu();
		System.out.print("선택:");
		int choice = stdIn.nextInt();
		switch(choice) {
		case 1:
			book.insertBook();
			break;
		case 2:
			System.out.println("코드\t제목\t작가\t재고숫자");
			book.searchBook();
			break;
		case 3:
			book.rent();
			break;
		case 4:
			book.returnBook();
			break;
		case 5:
			System.out.println("코드\t제목\t작가\t재고숫자");
			book.printBook();
			break;
		case 6:
			System.out.println("종료합니다.");
			stdIn.close();
			return;
		default:
			System.out.println("잘못누르셨습니다.\n다시선택해주세요");
			break;
		}
	}
}


	private static void printMenu() {
		System.out.println("1. 책 등록");
		System.out.println("2. 책 검색");
		System.out.println("3. 책 대여");
		System.out.println("4. 책 반납");
		System.out.println("5. 책 전체출력");
		System.out.println("6. 종료");
		System.out.println();
	}
}
