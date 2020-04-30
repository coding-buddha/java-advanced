package java8.in.action.ch05;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MathExample {
    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 5};

        // 필터링 조합
        // filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)

        // 집합 생성
        // filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
        //      .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});

        // b값 생성
        //IntStream.rangeClosed(1, 100)
        //        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
        //        .boxed()
        //        .map(b -> new int[]{a, b, (int) Math.sqrt(a*a + b * b)});

//        Stream<int[]> pythagoreanTriples =
//                IntStream.rangeClosed(1, 100).boxed()
//                        .flatMap(a -> IntStream.rangeClosed(a, 100)
//                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
//                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        Stream<double[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0));

        pythagoreanTriples.limit(10)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
