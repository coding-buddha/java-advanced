package java8.in.action.study;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class SupplierExample {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {

        /**
         * 아무런 인수도 받지 않고, 반환을 한다.
         */

        final Supplier<LocalDateTime> supplierTime = () -> LocalDateTime.now();
        System.out.println(supplierTime.get());         // 현재시간

        final Supplier<LocalDateTime> supplierFormatTime = () -> LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
        System.out.println(supplierFormatTime.get());   // 포맷팅 된 현재시간

        final Supplier<String> nameOfMeSupplier = () -> "PARK";
        System.out.println(nameOfMeSupplier.get());     // PARK
    }
}
