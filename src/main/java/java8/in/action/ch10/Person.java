package java8.in.action.ch10;

import java.util.Optional;

public class Person {

    private final String name;
    private final Wallet wallet;

    public Person(String name, Wallet wallet) {
        this.name = name;
        this.wallet = wallet;
    }

    public String getName() {
        return name;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
