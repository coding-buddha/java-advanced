package java8.in.action.ch03;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 실제 자원을 처리하는 코드를 설정과 정리 두 과정이 둘러싸는 형태
 * (1) -> (2) -> (3) : 실행어라운드 패턴
 * (1) 초기화/준비 코드
 * (2) 작업
 * (3) 정리/마무리 코드
 */
public class AroundPattern {

    public static void main(String[]args) throws IOException {

        // step01
        String result = ProcessFile.processFile();

        // step02 : processFile() 의 동작을 파라미터화 시켜야 한다.
        // BufferedReader -> String 와 IOException 을 던질 수 있는 시그니처와 일치하는 함수형 인터페이스를 만들어야 한다.
        result = ProcessFile.processFile((BufferedReader br) -> br.readLine() + br.readLine());

        // step03
        result = ProcessFile.processFile((BufferedReader br) -> br.readLine() + br.readLine() + br.readLine());
    }
}
