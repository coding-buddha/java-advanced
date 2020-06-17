package java8.in.action.ch07;

/**
 * 스레드 풀을 이용하기 위해선 RecursiveTask<R> 을 상속받아야 한다.
 */
public class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;    // 임계점 : 해당 값 이하로는 태스크 분리를 할 수 없다.

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(final long[]numbers, final int start, final int end){
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    /**
     * RecursiveTask 의 경우는 분할정복 형태로 값을 계산함.
     * - 태스크를 임계치에 도달할 때까지 계속 서브태스크로 나눈다.
     * - 임계치로 나눌 수 없는 수준에서는 해당 태스크 내에서 계산을 수행한다.
     * @return
     */
    @Override
    protected Long compute() {

        final int length = end - start;

        if(length <= THRESHOLD) {
            return computeSequentially();
        }

        ForkJoinSumCalculator leftSubTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);

        // ForkJoinPool 의 다른 스레드로 새로 생성한 태스크를 비동기로 실행한다.
        leftSubTask.fork();

        ForkJoinSumCalculator rightSubTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightSubTask.compute();  // 두번째 태스크를 동기실행한다.
        Long leftResult = leftSubTask.join();       // 첫번째 태스크 결과를 읽거나 또는 결과가 없을 시 기다린다.

        return rightResult + leftResult;
    }

    private long computeSequentially(){
        long sum = 0;
        for(int i = start; i < end; i++){
            sum += numbers[i];
        }
        return sum;
    }
}
