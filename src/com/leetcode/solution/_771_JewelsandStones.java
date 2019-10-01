package com.leetcode.solution;

import java.util.*;

public class _771_JewelsandStones {
    public int numJewelsInStones(String J, String S) {
        int res = 0;
        Set<Character> setJ = new HashSet<>();
        for (char j : J.toCharArray()) setJ.add(j);
        for (char s : S.toCharArray()) if (setJ.contains(s)) res++;
        return res;
    }
}
