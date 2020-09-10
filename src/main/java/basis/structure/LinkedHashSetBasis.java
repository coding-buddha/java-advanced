package basis.structure;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LinkedHashSetBasis {
    public static void main(String[] args) {

        /**
         * 셋이지만 순서에 맞에 데이터를 저장한다.
         */

        final Set<String> sets = new LinkedHashSet<>();
        sets.add("h");
        sets.add("a");
        sets.add("t");
        sets.add(" ");
        sets.add("d");
        sets.add("e");
        sets.add("s");
        sets.add("k");

        System.out.println("특정 문자 포함여부 : " + sets.contains("a"));

        final Iterator<String> it = sets.iterator();
        StringBuilder ret = new StringBuilder();
        while(it.hasNext()) {
            ret.append(it.next());
        }

        System.out.println("hat desk".equalsIgnoreCase(ret.toString()));
    }
}
