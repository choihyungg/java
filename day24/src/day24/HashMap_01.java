package day24;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMap_01 {
	//이름을 key로, 점수를 value로 저장하기

	public static void main(String[] args) {
		// Map 컬렉션 생성
		Map<String, Integer> map = new HashMap<String, Integer>();
		// <String, Integer> -> String 키로, Integer를 값으로 사용
		
		//객체 저장
		map.put("신용권", 85);
		map.put("홍길동", 90);
		map.put("동장군", 80);
		map.put("홍길동", 95); //key가 같기 때문에 제일 마지막 저장한 값으로 대체
		System.out.println("총 Entry 수: " + map.size());
		
		//객체 찾기
		//이름(key)으로 점수(value)를 검색
		System.out.println("\t홍길동 : " + map.get("홍길동")); //key로 정수 값 검색
		System.out.println();
		
		//객체를 하나씩 처리
		Set<String> ketSet = map.keySet(); //keySet() : 모든 키를 Set 객체에 담아서 리턴. key Set 얻기
		Iterator<String> keyIterator = ketSet.iterator();
		while (keyIterator.hasNext()) { //반복해서 키를 얻고 값을 Map에서 얻어냄
			String key = keyIterator.next();
			Integer value = map.get(key);
			System.out.println("\t" + key + " : " + value);
		}
		System.out.println();
		
		//foreach문을 사용해 출력.
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			String strKey = entry.getKey();
			Integer intValue = entry.getValue();
			System.out.println(strKey + ":" + intValue);
		}
		System.out.println();
		
		//객체 삭제
		map.remove("홍길동"); //키로 map.Entry를 제거
		System.out.println("총 Entry 수 : " + map.size()); //총 Entry 수 : 2
		
		//객체를 하나씩 처리
		//entrySet() : 키와 값의 쌍으로 구성된 모든 Map.Entry 객체를 Set에 담아서 리턴
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet(); // Map.Entry Set 얻기
		Iterator<Map.Entry<String, Integer>> entryIterator = entrySet.iterator();
		
		while (entryIterator.hasNext()) {
			Map.Entry<String, Integer> entry = entryIterator.next();
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("\t" + key + " : " + value);
		}
		System.out.println();
		
		//객체 전체 삭제
		map.clear();
		System.out.println("총 Entry 수: " + map.size()); //총 Entry 수 : 0
	}
}
