package java8.stream.exercise02;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public class Exercise02 {
    public static void main(String[] args) {
        /**
         * List.of("JDK 1.0", "J2SE 1.2", "J2SE 5.0", "Java SE 8", "Java SE 11", "Java SE 14");
         * List.of(1996, 1998, 2004, 2014, 2018, 2020);
         *
         * =========================================================================================
         *
         * "JDK 1.0 was released in 1996"
         * "J2SE 1.2 was released in 1998"
         * "J2SE 5.0 was released in 2004"
         * "Java SE 8 was released in 2014"
         * "Java SE 11 was released in 2018"
         * "Java SE 14 was released in 2020"
         */

        List<String> jdkVersions = Arrays.asList("JDK 1.0", "J2SE 1.2", "J2SE 5.0", "Java SE 8", "Java SE 11", "Java SE 14");
        List<Integer> years = Arrays.asList(1996, 1998, 2004, 2014, 2018, 2020);

        ZipUtil.zip(jdkVersions, years, Pair::new)
                .map(pair -> pair.getKey() + " was released in " + pair.getValue())
                .forEach(System.out::println);
    }
}
