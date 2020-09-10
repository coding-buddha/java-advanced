package basis.structure;

import basis.structure.ListBasis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("리스트 기초 클래스는")
class ListBasisTest {

    private final ListBasis listBasis = new ListBasis();


    @Test
    @DisplayName("서브리스트를 수행한다.")
    public void subListTest() {

        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        final List<Integer> ret = listBasis.subList(list, 3, 5);
        assertThat(ret).hasSize(2);
        assertThat(ret.get(0)).isSameAs(4);
        assertThat(ret.get(1)).isSameAs(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("값을 삽입하고 배열을 만든다.")
    public void generateListTest(final int value) {
        final List<Integer> list = listBasis.generateListByValue(value);
        assertThat(list).hasSize(1);

        listBasis.solution(new int[]{1, 2, 3, 4, 5});
    }
}