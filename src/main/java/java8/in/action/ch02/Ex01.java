package java8.in.action.ch02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex01 {

    public static void main(String[]args) {

        final List<Apple> apples = Arrays.asList(new Apple("green", 50),
                new Apple("green", 100),
                new Apple("green", 150),
                new Apple("red", 50),
                new Apple("red", 100),
                new Apple("red", 150));

        // 요구사항 1 : 녹색사과 필터링
        // 요구사항 2 : 색을 파라미터화 (좀 더 다양한 색상을 필터링)
        // 요구사항 3 : 무게기준에 따라 필터링
        // 요구사항 4 : 색과 무게를 기준으로 필터링
    }

    public static List<Apple> filterApplesStep01(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApplesStep02(List<Apple> inventory, String color){
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory){
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApplesStep03(List<Apple> inventory, int weight){
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory){
            if(apple.getWeight() > weight) {
                result.add(apple);
            }
        }

        return result;
    }

    /**
     * 형편없는 코드
     * - true 와 false 에 대한 명세기준이 없다.
     * - 사과의 크기, 모양, 출하지 등을 필터링 하고 싶다면 파라미터는 더 늘어날 수 밖에 없다.
     */
    public static List<Apple> filterApplesStep04(List<Apple> inventory, String color, int weight, boolean flag){
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory){
            if((flag && apple.getColor().equals(color))
                    || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }

        return result;
    }
}


