package day24;

class ServiceA {
	void runWork() {
		System.out.println("work");
	}
}

class ServiceB {
	void runStudy() {
		System.out.println("study");
	}
}

class AdapterServiceA {
	ServiceA serviceA = new ServiceA();
	void runService() {
		serviceA.runWork();
	}
}

class AdapterServiceB {
	ServiceB serviceB = new ServiceB();
	void runService() {
		serviceB.runStudy();
	}
}

public class AdapterNo_01 {

	public static void main(String[] args) {
		ServiceA serviceA = new ServiceA();
		ServiceB serviceB = new ServiceB();
		
		serviceA.runWork();
		serviceB.runStudy();
		
		AdapterServiceA asa1 = new AdapterServiceA();
		AdapterServiceB asb1 = new AdapterServiceB();
		
		//동일한 메서드 명을 사용할 수 있음.
		asa1.runService();
		asb1.runService();
	}
}
