package com.leetcode.solution;

import java.util.HashSet;
import java.util.Set;

class _217_Contains_Duplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int x : nums) {
            if (!set.contains(x)) {
                set.add(x);
            } else {
                return true;
            }
        }
        return false;
    }
}

