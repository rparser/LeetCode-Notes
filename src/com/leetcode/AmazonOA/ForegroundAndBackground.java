package com.leetcode.AmazonOA;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForegroundAndBackground {
    public List<List<Integer>> foreAndBack(int capa, List<List<Integer>> fore, List<List<Integer>> back) {
        if (fore.size() == 0 || back.size() == 0) return new ArrayList<>();
        int max = maxCap(capa, fore, back);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < fore.size(); i++) {
            for (int j = 0; j < back.size(); j++) {
                int total = fore.get(i).get(1) + back.get(j).get(1);
                if (total == max) result.add(Arrays.asList(fore.get(i).get(0), back.get(j).get(0)));
            }
        }
        return result;
    }

    public int maxCap(int capa, List<List<Integer>> fore, List<List<Integer>> back) {
        int maxCap = 0;
        for (int i = 0; i < fore.size(); i++) {
            for (int j = 0; j < back.size(); j++) {
                int total = fore.get(i).get(1) + back.get(j).get(1);
                if (total == capa) return total;
                if (total < capa && total > maxCap) maxCap = total;
            }
        }
        return maxCap;
    }


    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.AmazonOA.ForegroundAndBackground");
    }

    @Test
    public void testSolution() {
        int cap1 = 7;
        List<List<Integer>> fore1 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 4), Arrays.asList(3, 6));
        List<List<Integer>> back1 = Arrays.asList(Arrays.asList(1, 2));

        int cap2 = 10;
        List<List<Integer>> fore2 = Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 5), Arrays.asList(3, 7), Arrays.asList(4, 10));
        List<List<Integer>> back2 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4), Arrays.asList(4, 5));

        int cap3 = 15;
        List<List<Integer>> fore3 = Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 5), Arrays.asList(3, 7), Arrays.asList(4, 10));
        List<List<Integer>> back3 = Arrays.asList();

        int cap4 = 4;
        List<List<Integer>> fore4 = Arrays.asList(Arrays.asList(1, 9), Arrays.asList(2, 6), Arrays.asList(3, 18), Arrays.asList(4, 5));
        List<List<Integer>> back4 = Arrays.asList(Arrays.asList(1, 0), Arrays.asList(2, 0), Arrays.asList(3, 0), Arrays.asList(4, 0));

        int cap5 = 15;
        List<List<Integer>> fore5 = Arrays.asList(Arrays.asList(1, 6), Arrays.asList(2, 9), Arrays.asList(3, 18), Arrays.asList(4, 5));
        List<List<Integer>> back5 = Arrays.asList(Arrays.asList(1, 1), Arrays.asList(2, 1), Arrays.asList(3, 2), Arrays.asList(4, 2));

//        Assert.assertEquals(3, foreAndBack(cap1, fore1, back1));
//        Assert.assertEquals(10, foreAndBack(cap2, fore2, back2));
//        Assert.assertEquals(10, foreAndBack(cap3, fore3, back3));
//        Assert.assertEquals(10, foreAndBack(cap4, fore4, back4));
        Assert.assertEquals(10, foreAndBack(cap5, fore5, back5));

    }
}
