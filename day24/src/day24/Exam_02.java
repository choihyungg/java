package day24;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Exam_02 {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("blue", 96);
		map.put("hong", 86);
		map.put("white", 92);
		
		String name = null; //최고 점수를 받은 아이디 저장
		int maxScore = 0; //최고 점수 저장
		int totalScore = 0; //점수 합계 저장
		
		Iterator<String> entrySet = map.keySet().iterator();
		while (entrySet.hasNext()) {
			String key = entrySet.next();
			int thisScore = map.get(key);
			totalScore += thisScore;
			if (maxScore < thisScore) {
				maxScore = thisScore;
				name = key;
			}
		}
		System.out.println("평균 점수 : " + totalScore / map.size());
		System.out.println("최고 점수 : " + maxScore);
		System.out.println("최고 점수를 받은 아이디 : " + name);
		
		
		
//		for(Map.Entry<String, Integer> entry : map.entrySet()) {
//			Integer intValue = entry.getValue();
//			totalScore += intValue;
//		}
//		System.out.println("총점 : " + totalScore);
//		System.out.println("평균 점수 : " + totalScore / map.size());
//		
//		for(Map.Entry<String, Integer> entry : map.entrySet()) {
//			String strKey = entry.getKey();
//			Integer intValue = entry.getValue();
//			if (maxScore < intValue) {
//				maxScore = intValue;
//				name = strKey;
//			}
//		}
//		System.out.println("최고 점수 : " + maxScore);
//		System.out.println("최고 점수를 받은 아이디 : " + name);
		
//		평균 점수 : 91
//		최고 점수 : 96
//		최고 점수를 받은 아이디 : blue
	}

}
