package day27;

import java.util.Calendar;

public class java_util_test {
	public static String getDayToStr(int day) {
		String[] days = {"��", "��", "ȭ", "��", "��", "��", "��"};
		return days[day - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR); // ��
		int month = now.get(Calendar.MONTH) + 1; // ��
		int day = now.get(Calendar.DATE); // ��
		String strWeek = getDayToStr(now.get(Calendar.DAY_OF_WEEK)); //����
		String strAmPm = null; //���� / ����
		int hour = now.get(Calendar.HOUR); //��
		int minute = now.get(Calendar.MINUTE); //��
		int second = now.get(Calendar.SECOND); //��
		
		int amPm = now.get(Calendar.AM_PM);
		if (amPm == Calendar.AM) {
			strAmPm = "����";
		} else {
			strAmPm = "����";
		}
		
		System.out.print(year + "�� ");
		System.out.print(month + "�� ");
		System.out.println(day + "�� ");
		System.out.print(strWeek + "���� ");
		System.out.println(strAmPm + " ");
		System.out.print(hour + "�� ");
		System.out.print(minute + "�� ");
		System.out.println(second + "�� ");
	}
}
