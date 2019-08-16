package com.leetcode.solution;

import java.util.*;

/**
 * Time Complexity: O(n*n!) Note that there are n! permutations and it requires O(n) time to print a a permutation.
 * 思路： Key is to keep a used[i] array, mark the element already added. So we skip such ele in dfs
 * (can also be done through list.contains(i))
 */

public class _046_Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; //track if the digit already used
        dfs(res, new ArrayList<>(), nums, used, 0);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> path, int[] nums, boolean[] used, int level) {
        if (level == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) { 
            if (used[i])
                continue; //skip added element, eg: 123,back to point 1(added) 2(added) skip, then add 3, get123
            path.add(nums[i]); //i element placed in current recursion level = path.length,写着便于理解
            used[i] = true; //marked used
            dfs(res, path, nums, used, level + 1);
            path.remove(path.size() - 1); //return to last decision point
            used[i] = false;
        }
    }
}
