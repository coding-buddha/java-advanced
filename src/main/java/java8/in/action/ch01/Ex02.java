package java8.in.action.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Ex01 에 대한 코드 넘겨주기 예제
 */
public class Ex02 {

    public static void main(String[] args) {

        final List<Apple> apples = Arrays.asList(Apple.newRed(10), Apple.newGreen(50), Apple.newBrown(80));

        final List<Apple> greenApples = filterGreenApples(apples);

        final List<Apple> greenApplesOpts = filterApplesOpt(apples, Apple::isGreenApple);

        /**
         * 람다 표현식을 이용해서 프레디게이트를 활용할 수 있다.
         * ==> 메소드 참조를 이용하지 않아도 된다. !
         *  **/
        final List<Apple> filterApples1 = filterApplesOpt(apples, (Apple a) -> "green".equals(a.color));

        final List<Apple> filterApples2 = filterApplesOpt(apples, (Apple a) -> a.weight > 50);

        /**
         * 람다 표현식이 길어진다면 메소드 참조로 변경하는 것이 더 바람직하다.
         */
        final List<Apple> filterAPples3 = filterApplesOpt(apples, (Apple a) -> a.weight < 80 || "brown".equals(a.color));
    }

    // 필터링 하기 위한 많은 작업이 소요 : 여러 메소드를 만들어야 하는 작업이 필요.
    private static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> results = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.weight > 50) {
                results.add(apple);
            }
        }
        return results;
    }

    // 메소드 참조 수행 : 메소드를 전달할 수 있다.
    // predicate 란, 인수 값을 받아서 true 또는 false 를 전달함을 의미하고 수학에서의 표현식이다.
    private static List<Apple> filterApplesOpt(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> results = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                results.add(apple);
            }
        }
        return results;
    }

    static class Apple {
        String color;
        int weight;

        private Apple(String color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        static Apple newRed(int weight) {
            return new Apple("red", weight);
        }

        static Apple newGreen(int weight) {
            return new Apple("green", weight);
        }

        static Apple newBrown(int weight) {
            return new Apple("brown", weight);
        }

        public static boolean isGreenApple(Apple apple) {
            return "green".equalsIgnoreCase(apple.color);
        }

        public boolean isHeavyApple(Apple apple) {
            return apple.weight > 50;
        }
    }
}
