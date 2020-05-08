package java8.in.action.ch06;

import java8.in.action.model.Dish;

import java.util.*;

import static java.util.stream.Collectors.*;

public class CollectorPractice {
    public static void main(String[] args) {

        @SuppressWarnings("Duplicates")
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        /** 컬렉터로 할 수 있는 것들 **/
        long howManyDishes = menu.stream().count();
        howManyDishes = menu.size();

        /** 컬렉터는 다른 컬렉터와 사용할 때 위력을 발휘 **/

        /** (1) 스트림 값에서 최소값 또는 최댓값 찾기 **/
        final Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = menu.stream().collect(maxBy(dishComparator));
        Optional<Dish> leastCaloriesDish = menu.stream().min(dishComparator);

        System.out.println(mostCaloriesDish.get().toString());
        System.out.println(leastCaloriesDish.get().toString());

        /** (2) 요약 연산 : 스트림에 있는 객체와 숫자 필드의 합계나 평균 등을 반환하는 연산 **/

        // 합계를 반환.
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("totalCalories : " + totalCalories);
        totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("totalCalories : " + totalCalories);

        // 평균을 반환.
        double averCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println("averCalories : " + averCalories);

        // Integer 전용의 합계, 평균, 최대 및 최소값, 요소수 전체를 반환하는 메소드가 있음
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("menuStatistics : " + menuStatistics);

        /** (3) 문자열 연결 **/
        final String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println("ShortMenu : " + shortMenu);
    }
}