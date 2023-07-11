package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 求所有可能子集（无重复数字）
 * <p>
 * Time complexity :O(n*2^n).
 * Space complexity :O(n).
 */

public class _078_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._078Subsets");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(true, true);
        System.out.println((5) & (3));

    }
}
