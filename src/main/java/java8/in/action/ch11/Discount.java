package java8.in.action.ch11;

// 할인 서비스
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(final Quote quote) {
        return quote.getShopName() + "price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(final Long price, final Code code) {
        delay();
        return (double) price * (100 - code.percentage) / 100;
    }

    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
