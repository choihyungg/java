package day27;

import java.math.BigDecimal;

public class BigDecimal_01 {
	/* BigDecimal : ��Ȯ�� �Ҽ��� ������ �ؾ� �� �� BigDecimal Ŭ������ ���.
	 double, long���� �Ҽ��� ������ �� �� ������ ���� �κ��� ����. */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//double�� ����� ����� BigDecimal�� ����� ����� ��.
		double a = 24.3953;
		double b = 50.343998;
		System.out.println(a + b); //74.73929799999999
		
		BigDecimal number = new BigDecimal(String.valueOf(a));
		//double�� �ڷḦ ���ڿ��� ��ȯ�� BigDeciaml�� ��ü ����.
		BigDecimal number2 = new BigDecimal(String.valueOf(b));
		//add() �޼ҵ带 Ȱ���Ͽ� ��Ȯ�� ��� ���� ��ȯ.
		System.out.println(number.add(number2)); //74.739298

	}
}
