package java_util_Date;

import java.util.Date;

public class java_util_Date_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// java.util.Date
		Date today = new Date(); // �⺻ �����ڷ� Date �ν��Ͻ��� ����� ���.
		System.out.println(today);
		
		long a = System.currentTimeMillis();
		Date today2 = new Date(a); // long Ÿ���� �Ķ���ͷ� �޾Ƽ� �ν��Ͻ� ���� �� ���.
		System.out.println(today2);

	}

}
