package java8.in.action.ch06;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class NumberingPractice {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("소수를 판별할 숫자를 입력해주세요.");
        final int number = SCANNER.nextInt();
        System.out.println(number + " 은(는) 소수인가요 ? " + isPrimeForMiddle(number));

        System.out.println("소수를 구할 숫자의 범위를 입력해주세요.");
        final int includeSize = SCANNER.nextInt();
        System.out.println(Arrays.toString(partitioningByPrime(includeSize).get(true).toArray()));
    }

    public static boolean isPrimeForBeginner(int candidate){
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrimeForMiddle(int candidate) {
        final int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitioningByPrime(int includeSize){
        return IntStream.rangeClosed(2, includeSize)
                .boxed()
                .collect(partitioningBy(number -> isPrimeForMiddle(number)));
    }
}
