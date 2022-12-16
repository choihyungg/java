package java_util_Date;

import java.util.Date;

public class java_util_Date_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// java.util.Date
		Date today = new Date(); // 기본 생성자로 Date 인스턴스를 만들고 출력.
		System.out.println(today);
		
		long a = System.currentTimeMillis();
		Date today2 = new Date(a); // long 타입을 파라미터로 받아서 인스턴스 생성 후 출력.
		System.out.println(today2);

	}

}
