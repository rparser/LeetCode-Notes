package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

import java.util.*;

/**
 * 求所有可能子集（无重复数字）
 * <p>
 * Time complexity :O(n*2^n).
 * Space complexity :O(n).
 */

public class _078_Subsets {
    public List<List<Integer>> subsets(int[] S) {
        int totalNumber = 1 << S.length; //位运算 1<<n -> 1=2^n
        List<List<Integer>> result = new ArrayList<>(totalNumber);
        for (int i = 0; i < totalNumber; i++) { //i从0到2^n-1 (31,63 - 二进制全为1)，一共2^n种可能
            List<Integer> tempSet = new LinkedList<>();
            for (int j = 0; j < S.length; j++) {
                if ((i & (1 << j)) != 0) {
                    //i图样，和1<<j判断S[j]是否被选取（图样中必须有，且从0-s.length判断图样中是否为1）
                    tempSet.add(S[j]); //是则加入tempSet
                }
            }
            result.add(tempSet); // tempSet加入collection
        }
        return result;
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
