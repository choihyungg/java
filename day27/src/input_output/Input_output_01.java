package input_output;

import java.io.IOException;

public class Input_output_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* 표준 입출력
		: 화면에 출력하고 입력받는 표준 입출력 클래스
		static PrintStream out : 표준 출력 스트림
		static InputStream in : 표준 입력 스트림
		static OutputStream err : 표준 오류 출력 스트림
		*/
		
		System.out.println("알파벳 하나를 쓰고 [Enter]를 누르세요.");
		
		int i;
		try {
			//System.in : 표준 입력 스트림.
			i = System.in.read(); //read() 메서드로 한 바이트 읽음
			// System.out : 표준 출력 스트림.
			System.out.println(i); //int는 4바이트 이지만 1바이트만 읽어서 출력. 아스키 코드를 출력
			System.out.println((char)i); //문자로 변환하여 출력
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
