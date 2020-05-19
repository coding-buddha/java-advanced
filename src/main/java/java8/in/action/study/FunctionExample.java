package java8.in.action.study;

import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {

        /**
         * 제네릭 T 를 받아
         * 제네릴 R 을 리턴한다. 서로 다른 타입 무방
         * 하나를 받고 하나를 리턴
         */

        final Function<String, Integer> nameToNameLengthFunc = (name) -> name.length();
        System.out.println(nameToNameLengthFunc.apply("JAVA8"));            // 5
        System.out.println(nameToNameLengthFunc.apply("JAVA8In"));          // 7
        System.out.println(nameToNameLengthFunc.apply("JAVA8InAction"));    // 13

        /**
         * Chaning Function 이 가능하다.
         * **/
        final Function<Integer, Integer> addFunc = (x) -> x + 1;
        final Function<Integer, Integer> subFunc = (x) -> x - 1;
        final Function<Integer, Integer> expFunc = (x) -> x * x;

        final Function<Integer, Integer> newAddFunc = addFunc.andThen(addFunc);
        final Function<Integer, Integer> newSubFunc = subFunc.andThen(subFunc);

        System.out.println("Function Method Chaining");
        System.out.println(newAddFunc.apply(5));    // 7
        System.out.println(newSubFunc.apply(5));    // 3

        final Function<Integer, Integer> addAndThenExpFunc = addFunc.andThen(expFunc);
        System.out.println(addAndThenExpFunc.apply(7));     // 64

        final Function<Integer, Integer> expFuncThenAddFunc = expFunc.andThen(addFunc);
        System.out.println(expFuncThenAddFunc.apply(7));    // 50
    }
}
