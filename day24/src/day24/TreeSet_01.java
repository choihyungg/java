package day24;

import java.util.TreeSet;

public class TreeSet_01 {

	public static void main(String[] args) {
		// TreeSet �׽�Ʈ �ϱ�
		
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.add("ȫ�浿");
		treeSet.add("������");
		treeSet.add("�̼���");
		treeSet.add("������");
		
		for(String str : treeSet) {
			System.out.println(str);
		}
		
		//������������ ������ �Ǿ� ���
		//������
		//�̼���
		//ȫ�浿
	}
}