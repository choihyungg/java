package day27;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class LocalDate_04 {
	/* with() �޼���� TemporalAdjusters Ÿ���� ���ڷ� ������ Ư�� ��¥�� ����
	 firstDayOfYear() : �⵵�� ù ��° ��
	 LastDayOfYear() : �⵵�� ������ ��
	 firstDayOfMonth() : ���� ù ��° ��
	 LastDayOfMonth() : ���� ������ ��
	 firstInMonth(DayofWeek dayOfWeek) : ���� ù ��° ����
	 lastInMonth(DayofWeek dayOfWeek) : ���� ������ ����
	 next(DayofWeek dayOfWeek) : ���ƿ��� ����
	 nextOrSame(DayofWeek dayOfWeek) : ������ ������ ���ƿ��� ����
	 previous(DayofWeek dayOfWeek) : ���� ����
	 previousOrSam(DayofWeek dayOfWeek) : ������ ������ ���� ����  
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		LocalDateTime new_ldt;
		
		new_ldt = ldt.with(TemporalAdjusters.firstDayOfYear()); //�⵵�� ù ��° ��
		System.out.println("������ ù ��° �� : " + new_ldt);
		new_ldt = ldt.with(TemporalAdjusters.lastDayOfYear()); //�⵵�� ������ ��
		System.out.println("������ ������ �� : " + new_ldt);
		
		new_ldt = ldt.with(TemporalAdjusters.firstDayOfMonth()); //���� ù ��° ��
		System.out.println("�̹� ���� ù ��° �� : " + new_ldt);
		new_ldt = ldt.with(TemporalAdjusters.lastDayOfMonth()); //���� ������ ��
		System.out.println("�̹� ���� ������ �� : " + new_ldt);
		
		new_ldt = ldt.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); //���� ù��° ������
		System.out.println("�̹����� ù ������ : " + new_ldt);
		new_ldt = ldt.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY)); //���� ������ �Ͽ���
		System.out.println("�̹����� ������ �Ͽ��� : " + new_ldt);
		new_ldt = ldt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)); //���ƿ��� �ݿ���
		System.out.println("���ƿ��� �ݿ��� : " + new_ldt);
		new_ldt = ldt.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)); //������ ������ ���ƿ��� �ݿ���
		System.out.println("������ ������ ���� �ݿ��� : " + new_ldt);
		new_ldt = ldt.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)); //���� ������
		System.out.println("���� ������ : " + new_ldt);
		new_ldt = ldt.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)); //������ ������ ���� ������
		System.out.println("������ ������ ���� ������ : " + new_ldt);

	}

}
