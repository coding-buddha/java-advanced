package algorithm;

import java.util.*;

class Solution {

    public static void main(String[]args) {
        Solution solution = new Solution();
        solution.solution(new String[]{});
    }

    public int[] solution(String[] S) {
        // write your code in Java SE 8
        // [index1, index2, string-position]

        if(S.length == 0) {
            return new int[]{};
        }

        final int maxPosition = S[0].length() - 1;
        int currentPosition = 0;

        for(int p = currentPosition; p <= maxPosition; p++) {

            // char, index
            final Map<Character, Integer> store = new HashMap<>();

            for(int i = 0; i < S.length; i++) {
                final char c = S[i].charAt(p);
                final int foundIndex = store.getOrDefault(c, -1);
                if(foundIndex < 0) {
                    store.put(c, i);
                    continue;
                }

                return new int[]{foundIndex, i, p};
            }
        }

        return new int[]{};
    }
}