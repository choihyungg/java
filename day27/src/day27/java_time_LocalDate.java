package day27;

import java.time.LocalDateTime;

public class java_time_LocalDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�ð��� ���ϰ� ���� �޼��带 Ȯ���ϴ� �ڵ�
		//minusHours(long) �ð� ����, minusMinutes(long) �� ����, minusSeconds(long) �� ����, minusNanos(long) ������ ����
		//plushours(long) �ð� ���ϱ�, plusminutes(long) �� ���ϱ�, plusseconds(long) �� ���ϱ�, plusnanos(long) ������ ���ϱ�
		
		LocalDateTime localDateTime = LocalDateTime.now(); //���� �ð�
		System.out.println("���� �ð� : " + localDateTime); //���� �ð� : 2022-12-16T14:12:46.619949
		LocalDateTime localDateTime1 = localDateTime.minusHours(5).plusMinutes(30).minusSeconds(4);
		//5�ð��� ����, 30���� ���ϰ�, 4�ʸ� ��.
		System.out.println("���� �ð� : " + localDateTime1); //���� �ð� : 2022-12-16T09:42:42.619949
		
		LocalDateTime localDateTime2 = localDateTime1.minusHours(24);
		// ld2������ 24�ð��� ��
		System.out.println("�ڵ� ���� �ð� : " + localDateTime2); //�ڵ� ���� �ð� : 2022-12-15T09:42:42.619949
	}
}
