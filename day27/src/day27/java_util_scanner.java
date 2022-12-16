package day27;

import java.io.FileInputStream;
import java.util.Scanner;

public class java_util_scanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//파일로부터 입력받는 프로그램.
		//FileInputStream 클래스를 통해 파일을 처리하고 이를 Scanner 클래스를 통해 출력.
		try {
			FileInputStream fs = new FileInputStream("./sample_file/sample.txt");
			Scanner scanner = new Scanner(fs);
			while (scanner.hasNext()) { //값이 존재하면 계속해서 반복
				System.out.println(scanner.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
