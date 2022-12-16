package day27;

import java.time.LocalDateTime;

public class java_time_LocalDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//시간을 더하고 빼는 메서드를 확인하는 코드
		//minusHours(long) 시간 빼기, minusMinutes(long) 분 빼기, minusSeconds(long) 초 빼기, minusNanos(long) 나노초 빼기
		//plushours(long) 시간 더하기, plusminutes(long) 분 더하기, plusseconds(long) 초 더하기, plusnanos(long) 나노초 더하기
		
		LocalDateTime localDateTime = LocalDateTime.now(); //현재 시간
		System.out.println("현재 시간 : " + localDateTime); //현재 시간 : 2022-12-16T14:12:46.619949
		LocalDateTime localDateTime1 = localDateTime.minusHours(5).plusMinutes(30).minusSeconds(4);
		//5시간을 빼고, 30분을 더하고, 4초를 뺌.
		System.out.println("변경 시간 : " + localDateTime1); //변경 시간 : 2022-12-16T09:42:42.619949
		
		LocalDateTime localDateTime2 = localDateTime1.minusHours(24);
		// ld2값에서 24시간을 뺌
		System.out.println("자동 변경 시간 : " + localDateTime2); //자동 변경 시간 : 2022-12-15T09:42:42.619949
	}
}
