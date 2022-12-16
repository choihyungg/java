package day24;

import java.util.HashMap;
import java.util.Map;

class Student2 { // �й��� Ű�� ���ٸ� ������ Ű�� �ν�
	public int sno;
	public String name;
	
	public Student2(int sno, String name) {
		this.sno = sno;
		this.name = name;
	}

	@Override
	public int hashCode() { //�й��� �̸��� ���Ƹ� ������ ���� ����
		return sno + name.hashCode();
	}

	@Override
	public boolean equals(Object obj) { //�й��� �̸��� ���ٸ� true�� ��ȯ
		if(obj instanceof Student) {
			Student2 student = (Student2) obj;
			return (sno == student.sno) && (name.equals(student.name));
		}
		else {
			return false;
		}
	}
	
	
}

public class HashMap_02 {

	public static void main(String[] args) {
		Map<Student, Integer> map = new HashMap<Student, Integer>();
		
		//�й��� �̸��� ������ Student�� Ű�� ����.
		map.put(new Student(1, "ȫ�浿"), 95);
		map.put(new Student(1, "ȫ�浿"), 95);
		map.put(new Student(1, "������"), 85);
		
		System.out.println("�� Entry �� : " + map.size()); //����� �� Map.Entry �� ���.

	}

}
