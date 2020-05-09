package java8.in.action.ch06;

import java8.in.action.model.Dish;

import java.util.*;

import static java.util.stream.Collectors.*;

public class PartitionPractice {
    public static void main(String[]args) {

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

        /** 분할함수 : 프레디케이트를 이용하여 특수하게 그룹화한다. **/
        // 채식과 채식이 아닌 요리로 구분한다.
        final Map<Boolean, List<Dish>> partitionMenu = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));

        // true 는 채식요리, false 는 채식 이외의 요리
        System.out.println(Arrays.toString(partitionMenu.get(true).toArray()));
        System.out.println(Arrays.toString(partitionMenu.get(false).toArray()));

        /** 분할함수 이용 이후, 한번더 컬렉터를 적용한다. **/
        final Map<Boolean, Map<Dish.Type, List<Dish>>> partitionDishesByType = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));

        /** 채식과 채식이 아닌 요리에서 가장 칼로리가 높은 음식을 찾는다. **/
        final Map<Boolean, Dish> mostCaloricPartitionByVegetarian = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));

        // 채식과 채식 아닌 요리를 추출하고, 이후에 해당 요리들에서 칼로리가 500미만과 500이상 요리들만 선별한다.
        final Map<Boolean, Map<Boolean, List<Dish>>> prac01 = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));

        // 채식과 채식이 아닌 요리들을 카운팅 한다.
        final Map<Boolean, Long> countingByVegetarian = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, counting()));


    }
}
