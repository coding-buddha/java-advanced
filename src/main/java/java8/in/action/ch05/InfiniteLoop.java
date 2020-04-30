package java8.in.action.ch05;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InfiniteLoop {
    public static void main(String[]args) {
//        useIterateMethod();
//        createFiboUseIterateMethod();
        useGenerateMethod();
    }

    private static void useGenerateMethod() {

IntSupplier fib = new IntSupplier() {
    private int previous = 0;
    private int current = 1;

    @Override
    public int getAsInt() {
        int oldPrevious = this.previous;
        int nextValue = this.previous + this.current;
        this.previous = this.current;
        this.current = nextValue;
        return oldPrevious;
    }
};

IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

    private static void useIterateMethod(){
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    private static void createFiboUseIterateMethod() {
        Stream.iterate(new int[]{0, 1}, (array) -> new int[]{array[1], array[0] + array[1]})
                .limit(10)
                .forEach(t -> System.out.println(t[0]));
    }
}
