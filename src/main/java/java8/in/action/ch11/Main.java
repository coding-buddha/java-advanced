package java8.in.action.ch11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.processApplyDiscountServiceOptimize();
        main.processApplyDiscountService();
//        main.processBySync1();
//        main.processBySync2();
//        main.processByAsync();
    }

    // 할인서비스가 적용된 상태에서 동기작업과 비동기작업을 조합하여 딜레이 감소시키기.
    public void processApplyDiscountServiceOptimize() {
        final List<Shop> shops = Arrays.asList(new Shop("snack"), new Shop("pizza"), new Shop("cola"), new Shop("chicken"));
        final double startTime = System.nanoTime();

        final List<CompletableFuture<String>> futureResults = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(shop::getPriceByProduct))    // 각 상점에서 할인이전의 가격을 비동기적으로 획득
                .map(future -> future.thenApply(Quote::parse))                          // 상점에서 반환한 문자열을 quote 객체로 반환한다.

                // thenCompose 는 이전의 결과값을 가지고 CompletableFuture 에 를 한번더 반환토록 호출이 가능하다.
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote)))) // 결과 future 를 다른 비동기작업과 조합해서 할인코드를 적용한다.
                .collect(Collectors.toList());

        final List<String> results = futureResults.stream()
                .map(CompletableFuture::join)   // 리스트의 CompletableFuture 에 대해서 join 을 호출하여 모든 동작이 끝나기를 기다린다.
                .peek(System.out::println)
                .collect(Collectors.toList());

        final double invocationTime = ((System.nanoTime() - startTime) / 1_000_000_000);
        System.out.println("invocation time : " + invocationTime + " sec");
    }

    // 할인서비스 컴포넌트와 적용이 된 상태에서 딜레이 확인.
    public void processApplyDiscountService() {
        final List<Shop> shops = Arrays.asList(new Shop("snack"), new Shop("pizza"), new Shop("cola"), new Shop("chicken"));
        final double startTime = System.nanoTime();

        final List<String> results = shops.stream()
                .map(Shop::getPriceByProduct)
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .peek(System.out::println)
                .collect(Collectors.toList());

        final double invocationTime = ((System.nanoTime() - startTime) / 1_000_000_000);
        System.out.println("invocation time : " + invocationTime + " sec");
    }

    // parallelStream 을 이용
    public void processBySync1() {
        final List<Shop> shops = Arrays.asList(new Shop("snack"), new Shop("pizza"), new Shop("cola"), new Shop("chicken"));
        final double startTime = System.nanoTime();

        // stream 으로 할때와 parallelStream 으로 할 때 속도차이가 발생한다.
        final List<String> prices = shops.parallelStream()
                .map(shop ->
                    String.format("%s price is %d", shop.getProduct(), shop.getSyncPriceByProduct()))
                .peek(System.out::println)
                .collect(Collectors.toList());

        final double invocationTime = ((System.nanoTime() - startTime) / 1_000_000_000);
        System.out.println("invocation time : " + invocationTime + " sec");
    }

    // stream 을 이용한 상태에서 CompletableFuture 를 이용
    public void processBySync2() {
        final List<Shop> shops = Arrays.asList(new Shop("snack"), new Shop("pizza"), new Shop("cola"), new Shop("chicken"));
        final double startTime = System.nanoTime();

        final List<CompletableFuture<String>> completableFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getProduct() + "price is " + shop.getSyncPriceByProduct()))
                .collect(Collectors.toList());

        final List<String> results = completableFutures.stream()
                .map(CompletableFuture::join)
                .peek(System.out::println)
                .collect(Collectors.toList());

        final double invocationTime = ((System.nanoTime() - startTime) / 1_000_000_000);
        System.out.println("invocation time : " + invocationTime + " sec");
    }

    public void processByAsync() {
        final Shop shop = new Shop("snack");
        final double startTime = System.nanoTime();

        final Future<Long> future = shop.getSimpleAsyncPriceByProduct();
        final double invocationTime = ((System.nanoTime() - startTime) / 1_000_000_000);

        System.out.println("invocation time : " + invocationTime + " sec");

        // 제품의 가격을 계산하는 동안, 다른 작업을 수행한다고 가정
        shop.doSomething();

        try {
            // 콜백으로 호출됨.
            long price = future.get();
            System.out.println("price : " + price + "원");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        final double retrievalTime = ((System.nanoTime() - startTime) / 1_000_000_000);
        System.out.println("retrieval time : " + retrievalTime + " sec");
    }
}
