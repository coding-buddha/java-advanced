package java8.in.action.ch02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

        apples.sort(comparing(Apple::getWeight));
    }
}
