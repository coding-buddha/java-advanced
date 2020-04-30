package java8.in.action.ch04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Diff {
    public static void main(String[]args) {
        final List<String> list = Arrays.asList("Seoul", "Busan", "Daegu");
        final Stream<String> stream = list.stream();
        stream.forEach(System.out::println);

        // 컴파일 에러는 나지 않지만 두 번 이상 스트림을 재활용 하지못한다.
        // stream has already been operated upon or closed
        // stream.forEach(System.out::println);
    }
}
