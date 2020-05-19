package java8.in.action.study;

import java8.functional.ex01.BiFunction;

import java.util.function.BinaryOperator;

public class BinaryOperatorExample {
    public static void main(String[] args) {

        /**
         * [BinaryOperator]
         * 동일한 타입의 인수 두 개를 받아서 하나의 동일한 타입으로 리턴한다.
         *
         * [BiFunction]
         * 아무 타입의 인수 두 개를 받아서 하나의 아무 타입으로 리턴한다.
         */

        final BiFunction<Integer, Integer, Integer> addAndReturnValueFunc = (x, y) -> x + y;
        System.out.println(addAndReturnValueFunc.apply(5, 10));     // 15

        final BinaryOperator<Integer> addAndReturnValueFunc2 = (x, y) -> x + y;
        System.out.println(addAndReturnValueFunc2.apply(10, 20));   // 30


    }
}
