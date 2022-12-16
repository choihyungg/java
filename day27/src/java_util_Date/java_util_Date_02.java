package java_util_Date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class java_util_Date_02 {
	//SimpleDateFormat : 날짜를 형식화 하는 클래스.
	//Date와 Calendar에 있는 출력 형식 외에 원하는 형태로 출력하기 위한 클래스.
	//y : 년, M : 월, d : 일, E : 요일
	//a : 오전/오후, H : 시간, m : 분, s : 초

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date day = new Date();
		//한국, 미국, 영국에서 사용하는 날짜의 기본 형식이 다르므로 그 형식에 맞춰서 출력.
		String patternKorea = "yyyy-MM-dd";
		String patternUSA = "MM-dd-yyyy";
		String patternUK = "dd-MM-yyyy";
		String pattern1 = "E요일 HH시 mm분 ss초";
		
		//지정된 형식으로 SimpleDateFormat 객체 생성.
		SimpleDateFormat p1 = new SimpleDateFormat(patternKorea);
		SimpleDateFormat p2 = new SimpleDateFormat(patternUSA);
		SimpleDateFormat p3 = new SimpleDateFormat(patternUK);
		SimpleDateFormat p4 = new SimpleDateFormat(pattern1);
		
		System.out.println("현재날짜" + day);
		System.out.println("한국 형식(년, 월, 일) : " + p1.format(day));
		System.out.println("미국 형식(월, 일, 년) : " + p2.format(day));
		System.out.println("영국 형식(일, 월, 년) : " + p3.format(day));
		System.out.println(p4.format(day));
		System.out.println(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		// css나 js파일, 이미지의 경우 브라우저에서 캐시가 잡혀서 수정해도 바로 적용이 되지 않음.

	}

}
