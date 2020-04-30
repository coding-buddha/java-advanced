package java8.in.action.ch05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[]args){

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        final List<Transaction> list = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

        // (1) 2011 년에 일어난 모든 트랙잭션을 찾아 값을 오름차순으로 정리하시오.
        // (2) 거래자가 근무하는 도시를 중복 없이 나열하시오
        // (3) 케임브리지에서 근무하는 모든 거래자들을 찾아서 이름순으로 정렬하시오
        // (4) 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오
        // (5) 밀라노에 거래자가 있는가
        // (6) 케임브리지에 거주하는 거래자의 모든값을 트랜잭션 값으로 출력하시오.
        // (7) 전체 트랙잭션 중 최댓값은 얼마인가?
        // (8) 전체 트랙잭션 중 최소값은 얼마인가?

        // (1)
        List<Transaction> solveList01 = list.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        // (2)
        List<String> solveList02 = list.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        // (3)
        List<Trader> solveList03 = list.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        // (4)
        List<String> solveList04 = list.stream()
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName)
                .collect(Collectors.toList());

        // (5)
        boolean solve05 = list.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        // (6)
        List<Transaction> solveList06 = list.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.toList());

        // (7)
        int max = list.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max).get();

        // (8)
        int min = list.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min).get();

    }
}
