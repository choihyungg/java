package day27;

import java.util.Calendar;

public class java_util_test {
	public static String getDayToStr(int day) {
		String[] days = {"일", "월", "화", "수", "목", "금", "토"};
		return days[day - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR); // 년
		int month = now.get(Calendar.MONTH) + 1; // 월
		int day = now.get(Calendar.DATE); // 일
		String strWeek = getDayToStr(now.get(Calendar.DAY_OF_WEEK)); //요일
		String strAmPm = null; //오전 / 오후
		int hour = now.get(Calendar.HOUR); //시
		int minute = now.get(Calendar.MINUTE); //분
		int second = now.get(Calendar.SECOND); //초
		
		int amPm = now.get(Calendar.AM_PM);
		if (amPm == Calendar.AM) {
			strAmPm = "오전";
		} else {
			strAmPm = "오후";
		}
		
		System.out.print(year + "년 ");
		System.out.print(month + "월 ");
		System.out.println(day + "일 ");
		System.out.print(strWeek + "요일 ");
		System.out.println(strAmPm + " ");
		System.out.print(hour + "시 ");
		System.out.print(minute + "분 ");
		System.out.println(second + "초 ");
	}
}
