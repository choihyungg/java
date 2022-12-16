package day24;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class MyCompare implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		return (s1.compareTo(s2)) * -1; //String 클래스의 compareTo() 메서드를 반대로
	}
}

public class TreeSet_03 {

	public static void main(String[] args) {
		// Set<String> set = new TreeSet<String>();
		// TreeSet 생성자에 Comparator를 구현한 객체를 매개변수로 전달함.
		Set<String> set = new TreeSet<String>(new MyCompare());
		set.add("aaa");
		set.add("ccc");
		set.add("bbb");
		
		System.out.println(set); // [ccc, bbb, aaa]


	}

}
