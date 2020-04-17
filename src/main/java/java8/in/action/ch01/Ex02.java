package java8.in.action.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Ex01 에 대한 코드 넘겨주기 예제
 */
public class Ex02 {

    public static void main(String[]args) {

        final List<Apple> apples = Arrays.asList(Apple.newRed(10), Apple.newGreen(50), Apple.newBrown(80));

    }

    // 필터링 하기 위한 많은 작업이 소요 : 여러 메소드를 만들어야 하는 작업이 필요.
    private static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> results = new ArrayList<>();
        for(Apple apple : inventory){
            if(apple.weight > 50){
                results.add(apple);
            }
        }
        return results;
    }

    //
    private static List<Apple> filterGrennApplesOpt(List<Apple> inventory, Predicate<Apple> predicate){
        List<Apple> results = new ArrayList<>();
        for(Apple apple : inventory){
            if(predicate.test(apple)){
                results.add(apple);
            }
        }
        return results;
    }

    static class Apple {
        String color;
        int weight;

        private Apple(String color, int weight){
            this.color = color;
            this.weight = weight;
        }

        static Apple newRed(int weight){
            return new Apple("red", weight);
        }

        static Apple newGreen(int weight){
            return new Apple("green", weight);
        }

        static Apple newBrown(int weight){
            return new Apple("brown", weight);
        }

        public boolean isGreenApple(Apple apple){
            return "green".equalsIgnoreCase(apple.color);
        }

        public boolean isHeavyApple(Apple apple){
            return apple.weight > 50;
        }
    }
}
