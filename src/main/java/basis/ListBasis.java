package basis;

import java.util.List;

public class ListBasis {

    public List<Integer> subList(final List<Integer> list, final int fromIndex, final int toIndex) {
        // from index 는 포함되고
        // to index 는 포함되지 않는다.
        return list.subList(fromIndex, toIndex);
    }
}
