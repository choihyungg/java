package day25;

public class Singleton_01 {

	public static void main(String[] args) {
		// 4.�ܺο��� ����ϴ� �ڵ� �����.
		// �ܺ� Ŭ�������� Company�� ������ �� �����Ƿ� static���� �����Ǵ� getInstance() �޼��带 ȣ��.
		Company company1 = Company.getInstance();
		Company company2 = Company.getInstance();
		//Company company = new Company();
		
		System.out.println(company1 == company2); //�� ������ ���� �ּ����� Ȯ��.
		System.out.println(company1); //day25.Company@1c4af82c
		System.out.println(company2); //day25.Company@1c4af82c

	}

}
