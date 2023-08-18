package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 求所有可能子集（无重复数字）
 * <p>
 * Time complexity :O(n*2^n).
 * Space complexity :O(n).
 */

public class _078_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> resultList, List<Integer> tempList, int[] nums, int start) {
        resultList.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(resultList, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static List<List<Integer>> findSubsets(int[] nums, int K) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackRange(nums, K, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackRange(int[] nums, int K, int start, List<Integer> current, List<List<Integer>> result) {
        if (!current.isEmpty()) {
            int min = current.get(0);
            int max = current.get(current.size() - 1);
            if (min + max >= K) {
                return; // If min + max exceeds K, stop recursion
            }
        }

        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrackRange(nums, K, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int K = 7;

        List<List<Integer>> subsets = findSubsets(nums, K);

        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }
}
