package java8.stream;

public class LazyEvaluationExample {
    public static void main(String[] args) {

    }
}

class Coffee {

    public static final Coffee NONE_COFFEE = new Coffee(0L);

    private final String name;
    private final Long price;

    public Coffee(final Long price) {
        this("", price);
    }

    public Coffee(final String name, final Long price) {
        this.name = name;
        this.price = price;
    }

    public long getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public void makeCoffee() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}