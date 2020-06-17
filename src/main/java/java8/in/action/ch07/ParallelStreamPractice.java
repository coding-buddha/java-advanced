package java8.in.action.ch07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ParallelStreamPractice {
    public static void main(String[] args) {

        System.out.println(forkJoinSum(10000000L));
    }

    private static long forkJoinSum(final long n){
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);

        // RecursiveTask 내에서는 invoke() 메소드를 사용하면 안된다.
        // 내부에서는 fork() 나 compute() 를 직접 호출할 수 있어야 한다.
        return new ForkJoinPool().invoke(task);
    }
}
