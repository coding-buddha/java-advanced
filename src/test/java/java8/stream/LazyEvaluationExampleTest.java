package java8.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

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
                .filter(coffee -> coffee.getPrice() < 30000)
                .findFirst()
                .orElse(Coffee.NONE_COFFEE);

        // then
        assertThat(foundCoffee.getName()).isEqualTo("tongyeong-coffee");
    }
}