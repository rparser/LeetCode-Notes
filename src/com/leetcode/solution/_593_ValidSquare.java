package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.*;

/**
 * 有效正方形
 * Time complexity : O(1)O(1). A fixed number of comparisons are done.
 * Space complexity : O(1)O(1). No extra space required.
 * <p>
 * 计算list中有效的正方形
 */

public class _593_ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashMap<Double, Integer> map = new HashMap<>(); //距离，存在的个数
        double dist12 = dist(p1, p2);
        double dist13 = dist(p1, p3);
        double dist14 = dist(p1, p4);
        double dist23 = dist(p2, p3);
        double dist24 = dist(p2, p4);
        double dist34 = dist(p3, p4);

        map.put(dist12, map.getOrDefault(dist12, 0) + 1);
        map.put(dist13, map.getOrDefault(dist12, 0) + 1);
        map.put(dist14, map.getOrDefault(dist12, 0) + 1);
        map.put(dist23, map.getOrDefault(dist12, 0) + 1);
        map.put(dist24, map.getOrDefault(dist12, 0) + 1);
        map.put(dist34, map.getOrDefault(dist12, 0) + 1);

        if ((map.size() == 2)  //必须只能有两个长度
                && ((map.get(dist12) == 4 && dist13 / dist12 * dist14 / dist12 * dist23 / dist12 * dist24 / dist12 * dist34 / dist12 == 4) //12为四周的边
                || (map.get(dist12) == 2 && dist12 / dist13 * dist12 / dist14 * dist12 / dist23 * dist12 / dist24 * dist12 / dist34 == 16))) //12为对角线
            return true;
        return false;//不是两个长度就返回false
    }

    private double dist(int[] p1, int[] p2) { //didn't calculate square root
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }

    public List allValidSquare(List<int[]> input) {
        HashMap<Double, List<String>> map = new HashMap<>(); //距离，存在的个数
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) { //n^2复杂度
            for (int j = i + 1; j < input.size(); j++) {
                double distIj = dist(input.get(i), input.get(j));
                if (map.containsKey(distIj)) map.get(distIj).add(i + "," + j);
                else {
                    map.put(distIj, new ArrayList<>());
                    map.get(distIj).add(i + "," + j);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._593_ValidSquare");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(4, validSquare(null, null, null, null));
    }
}
