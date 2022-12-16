package day25;

public class Singleton_01 {

	public static void main(String[] args) {
		// 4.외부에서 사용하는 코드 만들기.
		// 외부 클래스에서 Company를 생성할 수 없으므로 static으로 제공되는 getInstance() 메서드를 호출.
		Company company1 = Company.getInstance();
		Company company2 = Company.getInstance();
		//Company company = new Company();
		
		System.out.println(company1 == company2); //두 변수가 같은 주소인지 확인.
		System.out.println(company1); //day25.Company@1c4af82c
		System.out.println(company2); //day25.Company@1c4af82c

	}

}
