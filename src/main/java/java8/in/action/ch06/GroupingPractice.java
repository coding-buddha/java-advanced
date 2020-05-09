package java8.in.action.ch06;

import java8.in.action.model.CaloricLevel;
import java8.in.action.model.Dish;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class GroupingPractice {
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

        /** (1) 그룹화 방식 **/
        final Map<Dish.Type, List<Dish>> dishesType = menu.stream().collect(groupingBy(Dish::getType));

        /** (2) 커스텀하게 그룹화 방식 : 새로운 키 값을 삽입하여 구함 **/
        final Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
                .collect(groupingBy(dish -> {
                    if(dish.getCalories() <= 400){
                        return CaloricLevel.DIET;
                    } else if(dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
        }));

        /** (3) 다수준 그룹화 방식 **/
        final Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if(dish.getCalories() <= 400){
                                return CaloricLevel.DIET;
                            } else if(dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        })));

        /** (4.1) 서브그룹으로 데이터 수집 **/
        // 메뉴에서 요리별 종류의 개수를 획득 할 수 있다.
        final Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));

        /** (4.2) **/
        // 요리별 종류에서 가장 높은 칼로리를 가진 요리 하나만 선정.
        final Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));

        /** (4.3) 4.2 의 결과를 다른 형식으로 적용시키기 **/
        final Map<Dish.Type, Dish> mostCaloricByTypeOne = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));

        /** (4.4) groupingBy 를 이용해서 다른 컬렉터 반환하기 : mapping 과 toSet() 을 결합 **/
        final Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType = menu.stream()
                .collect(groupingBy(Dish::getType, mapping(dish -> {
                    if(dish.getCalories() <= 400){
                        return CaloricLevel.DIET;
                    } else if(dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }, toSet())));
    }
}
