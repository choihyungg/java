package day27;

import java.time.LocalDateTime;

public class LocalDate_05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* 클래스들의 정보 값 비교하기
		 time 패키지에서는 각 필드 값을 비교하는 메서드를 사용 가능.
		 isAfter() : 이전의 날짜인지 비교하여 boolean값 반환
		 isBefore() : 지나간 날짜인지 비교하여 boolean값 반환
		 isEqual() : 동일 날짜인지 비교하여 boolean값 반환
		 until() : 날짜나 시간의 차이를 반환
		 between() : 전체 날짜나 시간의 차이를 반환
		 */
		
		LocalDateTime ldt1 = LocalDateTime.of(2010, 1, 1, 12, 23, 23, 444);
		System.out.println(ldt1);
		LocalDateTime ldt2 = LocalDateTime.of(2010, 12, 25, 1, 12, 2, 232);
		System.out.println(ldt2);
		
		//ldt1이 ldt2보다 이후의 날짜인가?
		System.out.println(ldt1.isAfter(ldt2)); //false
		//ldt1이 ldt2 이전의 날짜인가?
		System.out.println(ldt1.isBefore(ldt2)); //true
		//같은 날짜인가?
		System.out.println(ldt1.isEqual(ldt2)); //false
		
		

	}

}
