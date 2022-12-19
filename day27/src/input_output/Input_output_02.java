package input_output;

import java.io.IOException;

public class Input_output_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//표준 입출력
		//화면에 출력하고 입력받는 표준 입출력 클래스
		
		//문자 여러 개를 입력 받기
		System.out.println("알파벳 여러 개를 쓰고 [Enter]를 누르세요");
		
		int i;
		try {
			//read()의 반환형은 int.
			while ((i = System.in.read()) != -1) { // System.in : 표준 입력 스트림
				System.out.print((char)i); //System.out : 표준 출력 스트림.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
