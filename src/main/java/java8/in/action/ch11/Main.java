package java8.in.action.ch11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.processBySync1();
        main.processBySync2();
//         main.processByAsync();
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
