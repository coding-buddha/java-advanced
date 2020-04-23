package java8.in.action.ch02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex02 {

    public static void main(String[]args) {

        /**
         * Ex01 에 있는 사과 필터링을 최적화한다.
         */

        final List<Apple> apples = Arrays.asList(new Apple("green", 50),
                new Apple("green", 100),
                new Apple("green", 150),
                new Apple("red", 50),
                new Apple("red", 100),
                new Apple("red", 150));

        final List<Apple> filteringApples = filterApplesStep01(apples, new AppleGreenColorPredicate());
    }

    /**
     * 추상적 조건으로 필터링 수행 : 다양한 ApplePredicate 를 만들 수 있다.
     * ==> 결국 메소드의 동작을 파라미터화 시켰다.
     */
    public static List<Apple> filterApplesStep01(List<Apple> inventory, ApplePredicate predicate){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(predicate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
