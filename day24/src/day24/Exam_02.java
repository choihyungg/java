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
		
		String name = null; //�ְ� ������ ���� ���̵� ����
		int maxScore = 0; //�ְ� ���� ����
		int totalScore = 0; //���� �հ� ����
		
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
		System.out.println("��� ���� : " + totalScore / map.size());
		System.out.println("�ְ� ���� : " + maxScore);
		System.out.println("�ְ� ������ ���� ���̵� : " + name);
		
		
		
//		for(Map.Entry<String, Integer> entry : map.entrySet()) {
//			Integer intValue = entry.getValue();
//			totalScore += intValue;
//		}
//		System.out.println("���� : " + totalScore);
//		System.out.println("��� ���� : " + totalScore / map.size());
//		
//		for(Map.Entry<String, Integer> entry : map.entrySet()) {
//			String strKey = entry.getKey();
//			Integer intValue = entry.getValue();
//			if (maxScore < intValue) {
//				maxScore = intValue;
//				name = strKey;
//			}
//		}
//		System.out.println("�ְ� ���� : " + maxScore);
//		System.out.println("�ְ� ������ ���� ���̵� : " + name);
		
//		��� ���� : 91
//		�ְ� ���� : 96
//		�ְ� ������ ���� ���̵� : blue
	}

}
