package java8.in.action.study;

import java.util.function.BiConsumer;

public class BiConsumerExample {
    public static void main(String[] args) {

        /**
         * 두 개의 인수를 받고 아무것도 리턴하지 않는다.
         */

        final BiConsumer<Integer, Integer> printAddTwoNumber = (x, y) -> System.out.println(x + y);
        printAddTwoNumber.accept(2, 5); // 7

        final BiConsumer<Integer, Integer> printMulTwoNumber = (x, y) -> System.out.println(x * y);
        printMulTwoNumber.accept(2, 5); // 10

        System.out.println("=========");
        final BiConsumer<Integer, Integer> printAndThen = printAddTwoNumber.andThen(printMulTwoNumber);
        printAndThen.accept(2, 5);      // 7, 10
    }
}
