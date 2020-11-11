package basis.structure;

import java.util.*;


public class ListBasis <T>{

    public int getFistIndexByElement(final List<T> list, final T element) {
        return list.indexOf(element);
    }

    public List<T> subList(final List<T> list, final int fromIndex, final int toIndex) {
        // from index 는 포함되고
        // to index 는 포함되지 않는다.

        return list.subList(fromIndex, toIndex);
    }

    public List<T> generateListByValue(final T value) {
        return Collections.singletonList(value);
    }   
}
