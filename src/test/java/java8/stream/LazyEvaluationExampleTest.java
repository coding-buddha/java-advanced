package java8.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://www.logicbig.com/tutorials/core-java-tutorial/java-util-stream/lazy-evaluation.html
 * https://www.javaprogramto.com/2019/06/java-8-stream-intermediate-operations.html
 * https://www.javaprogramto.com/2020/04/java-8-14-stream-terminal-operations.html
 */
@DisplayName("LazyEvaluationExample 클래스는")
class LazyEvaluationExampleTest {

    @Test
    @DisplayName("전체 커피들을 확인하지 않는다.")
    public void lazyEvaluationTest() {

        // given
        final List<Coffee> coffees = Arrays.asList(
                new Coffee("seoul-coffee", 40000L),
                new Coffee("tongyeong-coffee", 10000L),
                new Coffee("jeju-coffee", 30000L),
                new Coffee("gangneung-coffee", 25000L));

        // when
        final Coffee foundCoffee = coffees.stream()
                .filter(Objects::nonNull)
                .peek(coffee -> System.out.println("(1) coffee : " + coffee))
                .filter(coffee -> coffee.getPrice() == 30000)
                .peek(coffee -> System.out.println("(2) coffee : " + coffee))
                .findFirst()
                .orElse(Coffee.NONE_COFFEE);
        System.out.println("커피들을 필터링한다.");

        // then
        assertThat(foundCoffee.getName()).isEqualTo("jeju-coffee");
    }

    @Test
    @DisplayName("전체 커피들을 확인하지 않는다.")
    public void eagerEvaluationTest() {

        // given
        final List<Coffee> coffees = Arrays.asList(
                new Coffee("seoul-coffee", 40000L),
                new Coffee("tongyeong-coffee", 10000L),
                new Coffee("jeju-coffee", 30000L),
                new Coffee("gangneung-coffee", 25000L));

        // when
        Coffee foundCoffee = Coffee.NONE_COFFEE;
        for(Coffee coffee : coffees) {
            final boolean isNonNull = Objects.nonNull(coffee);

            if(!isNonNull) {
                continue;
            }

            System.out.println("(1) coffee : " + coffee);
            if(coffee.getPrice() == 30000L) {
                System.out.println("(2) coffee : " + coffee);
                foundCoffee = coffee;
                break;
            }
        }

        System.out.println("커피들을 필터링한다.");

        // then
        assertThat(foundCoffee.getName()).isEqualTo("jeju-coffee");
    }
}