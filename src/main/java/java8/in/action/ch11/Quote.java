package java8.in.action.ch11;

public class Quote {
    private final String shopName;
    private final long price;
    private final Discount.Code discountCode;

    public Quote(String shopName, long price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote parse(final String s) {
        final String[] split = s.split(":");
        final String shopName = split[0];
        final long price = Long.parseLong(split[1]);
        final Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

    public String getShopName() {
        return this.shopName;
    }

    public Long getPrice() {
        return this.price;
    }

    public Discount.Code getDiscountCode() {
        return this.discountCode;
    }
}
