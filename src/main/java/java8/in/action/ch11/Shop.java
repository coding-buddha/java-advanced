package java8.in.action.ch11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

// 상점
public class Shop {

    private final String product;

    public Shop(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void doSomething() {
        System.out.println("do something...");
    }

    public String getPriceByProduct() {
        final long price = getPrice();
        final Discount.Code code = Discount.Code.values()[ThreadLocalRandom.current().nextInt(1, Discount.Code.values().length)];
        return String.format("%s:%d:%s", product, price, code);
    }

    // 동기 방식
    public Long getSyncPriceByProduct() {
        return getPrice();
    }

    // 비동기 방식
    public Future<Long> getAsyncPriceByProduct() {
        CompletableFuture<Long> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            try {
                final long price = getPrice();
                completableFuture.complete(price);
            } catch (Exception e) {
                // 도중에 에러 발생 시, 에러를 포함시켜서 Future 를 종료한다.
                completableFuture.completeExceptionally(e);
            }
        }).start();

        return completableFuture;
    }

    // getAsyncPriceByProduct() 메소드와 내용이 유사하다.
    public Future<Long> getSimpleAsyncPriceByProduct() {
        return CompletableFuture.supplyAsync(this::getPrice);
    }

    private Long getPrice() {
        delay();
//        throw new RuntimeException("강제 에러 발생.");
        return ThreadLocalRandom.current().nextLong(1L, 100000L);
    }

    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
