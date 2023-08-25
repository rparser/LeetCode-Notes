package com.leetcode.solution;

import java.util.HashSet;
import java.util.Set;

public class _2357_Make_Array_Zero_by_Subtracting_Equal_Amounts {
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        return set.size();
    }
}
