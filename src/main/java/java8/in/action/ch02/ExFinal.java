package java8.in.action.ch02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparing;

public class ExFinal {

    public static void main(String[]args) {
        final List<Apple> apples = Arrays.asList(new Apple("green", 50),
                new Apple("green", 100),
                new Apple("green", 150),
                new Apple("red", 50),
                new Apple("red", 100),
                new Apple("red", 150));

        // 클래스름 만들어서 소팅
        apples.sort(new AppleComparator());

        // 익명클래스를 만들어서 소팅
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple apple1, Apple apple2) {
                return apple1.getWeight() - apple2.getWeight();
            }
        });

        // 람다 표현식을 이용해서 소팅
        apples.sort((Apple apple1, Apple apple2) -> apple1.getWeight() - apple2.getWeight());

        // 타입 추론
        apples.sort((apple1, apple2) -> apple1.getWeight() - apple2.getWeight());

        apples.sort(Comparator.comparingInt(Apple::getWeight));

        // 무게 오름차순
        apples.sort(comparing(Apple::getWeight));

        // 무게 내림차순 (오름차순의 역정렬)
        apples.sort(comparing(Apple::getWeight).reversed());

        // 무게 역정렬하면서 동일 무게에 대해선 색깔 정렬을 수행
        apples.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));

        Function<Integer, Integer> f = (x) -> x + 1;
        Function<Integer, Integer> g = (x) -> x * 2;
        Function<Integer, Integer> h1 = f.compose(g);   // f(g(x))
        Function<Integer, Integer> h2 = f.andThen(g);   // g(f(x))

        System.out.println(h1.apply(1));    // 4
        System.out.println(h2.apply(1));    // 3
    }
}
