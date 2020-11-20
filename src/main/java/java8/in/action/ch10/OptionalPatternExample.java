package java8.in.action.ch10;

import java.util.*;

public class OptionalPatternExample {

    public static void main(String[]args) {

        /**
         * richPerson 은 지갑안에 50000원이 있다.
         */
        Person richPerson = new Person("PARK", new Wallet(new Money(50000L)));
        Optional<Wallet> w1 = Optional.of(richPerson.getWallet());
        Optional<Money> m1 = w1.map(Wallet::getMoney);
        System.out.println("result1 : " + ((m1.isPresent()) ? "지갑에 돈이 있습니다." : "지갑에 돈이 한 푼도 없습니다."));

        /**
         * poorPerson 은 지갑안에 아무것도 없다.
         */
        Person poorPerson = new Person("PARK", new Wallet());
        Optional<Wallet> w2 = Optional.of(poorPerson.getWallet());
        Optional<Money> m2 = w2.map(Wallet::getMoney);
        System.out.println("result2 : " + ((m2.isPresent()) ? "지갑에 돈이 있습니다." : "지갑에 돈이 한 푼도 없습니다."));

        List<Integer> list = Arrays.asList(0);
        list.set(0, 5);

        /**
         * map 의 간결화
         * 계속에서 Optional 을 중첩해서 get() 으로 획득하고 다시 of() 로 감싸주어야 한다.
         */
//        Optional<Person> optionalPerson = Optional.of(new Person("PARK", new Wallet(new Money(1000L))));
//        Optional<Wallet> optionalWallet = Optional.of(optionalPerson.get().getWallet());
//        Money optionalMoney = Optional.of(optionalWallet.get().getMoney()).orElse(new Money(0L));

        /**
         * 간결화
         */
        Optional<Person> optionalPerson = Optional.of(new Person("PARK", new Wallet(new Money(1000L))));
        Money currentMoney = optionalPerson
                .map(Person::getWallet)
                .map(Wallet::getMoney)
                .orElse(new Money(0L));
    }

    public List<Integer> getRow(int rowIndex) {
        if(rowIndex == 0) {
            return Collections.singletonList(1);
        }

        if(rowIndex == 1) {
            return Arrays.asList(1, 1);
        }

        List<Integer> list = Arrays.asList(1, 2, 1);
        return process(rowIndex, 2, list);
    }

    private List<Integer> process(final int targetIndex, final int currentIndex, final List<Integer> ret) {
        if(currentIndex == targetIndex) {
            return ret;
        }

        ret.add(1);
        int len = ret.size() - 1;

        List<Integer> newRet = new ArrayList<>();
        newRet.add(1);
        for(int i = 1; i < len - 1; i++) {
            newRet.add(ret.get(i - 1) + ret.get(i));
        }
        newRet.add(1);

        return process(targetIndex, currentIndex + 1, newRet);
    }
}


