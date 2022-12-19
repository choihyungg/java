package input_output;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStream_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* FileInputStream
		: 파일에서 바이트 단위로 자료를 읽어 들일 때 사용하는 스트림 클래스.
		
		FileInputStream(String name) : 파일 이름 name (경로 포함)을 매개 변수로 받아 입력 스트림을 생성.
		FileInputStream(File f) : File 클래스 정보를 매개변수로 받아 입력 스트림을 생성.
		*/		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("./sample_file/input.txt"); // input.txt 파일 스트림 생성
			
			//read() 메서드의 반환형은 int. 한 바이트를 읽어서 int에 저장. 더 이상 읽어 드릴 자료가 없는 경우 -1반환.
			System.out.println((char)fis.read());
			System.out.println((char)fis.read());
			System.out.println((char)fis.read());
			
		} catch (IOException e) {
			System.out.println(e);
		}
		finally {
			try {
				if (fis != null) {
					fis.close(); //열린 스트림은 finally 블록에서 닫음
				}
			} catch (IOException | NullPointerException e) {
				System.out.println(e);
			}
			//스트림이 null인 경우
		}
		System.out.println("end"); //end

	}

}
