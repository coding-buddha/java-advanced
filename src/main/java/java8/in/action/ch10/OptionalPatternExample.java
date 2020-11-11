package java8.in.action.ch10;

import java.util.Optional;

public class OptionalPatternExample {

    public static void main(String[]args) {

        /**
         * richPerson 은 지갑안에 50000원이 있다.
         */
        Person richPerson = new Person("PARK", new Wallet(new Money(50000L)));
        Optional<Wallet> w1 = Optional.of(richPerson.getWallet());
        Optional<Money> m1 = w1.map(Wallet::getMoney);
        System.out.println("result1 : " + m1.isPresent());

        /**
         * poorPerson 은 지갑안에 아무것도 없다.
         */
        Person poorPerson = new Person("PARK", new Wallet());
        Optional<Wallet> w2 = Optional.of(poorPerson.getWallet());
        Optional<Money> m2 = w2.map(Wallet::getMoney);
        System.out.println("result2 : " + m2.isPresent());

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
}
