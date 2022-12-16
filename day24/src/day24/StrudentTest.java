package day24;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StrudentTest {

	public static void main(String[] args) {
		Set<Student> set = new HashSet<Student>();
		
		set.add(new Student(1, "홍길동"));
		set.add(new Student(2, "신용권"));
		set.add(new Student(1, "조민우")); //학번이 같아서 저장되지 않음
		
		Iterator<Student> iterator = set.iterator();
		while (iterator.hasNext()) {
			Student student = iterator.next();
			System.out.println(student.studentNum + "-" + student.name);
		}	
		/*
		 1-홍길동
		 2-신용권
		 */
	}

}
