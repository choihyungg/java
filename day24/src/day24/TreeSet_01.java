package day24;

import java.util.TreeSet;

public class TreeSet_01 {

	public static void main(String[] args) {
		// TreeSet 테스트 하기
		
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.add("홍길동");
		treeSet.add("강감찬");
		treeSet.add("이순신");
		treeSet.add("강감찬");
		
		for(String str : treeSet) {
			System.out.println(str);
		}
		
		//오름차순으로 정렬이 되어 출력
		//강감찬
		//이순신
		//홍길동
	}
}