package java8.in.action.ch10;

public class Wallet {

    private Money money = null;

    public Wallet(final Money money) {
        this.money = money;
    }

    public Wallet() {}

    public Money getMoney() {
        return money;
    }
}
