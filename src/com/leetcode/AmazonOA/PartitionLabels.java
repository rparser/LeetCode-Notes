package com.leetcode.AmazonOA;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartitionLabels {
    public List<Integer> partitionLabels(List<Character> inputList) {
        int[] last = new int[256]; // 记录字母最后的位置
        for (int i = 0; i < inputList.size(); i++) // 遍历第一次记录位置
            last[inputList.get(i)] = i;
        int end = 0, start = 0; // 设置开始和结束点
        List<Integer> ans = new ArrayList<>(); // ArrayList记录结果
        for (int i = 0; i < inputList.size(); i++) { // 遍历第二次
            end = Math.max(end, last[inputList.get(i)]); // 不断向后推end，推到不动为止
            if (i == end) { // i==end时，开始新片段
                ans.add(i - start + 1); // 结果加入ArrayList
                start = i + 1; // start点从下一个开始
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.AmazonOA.PartitionLabels");
    }

    @Test
    public void testSolution() {
        List<Character> inputList = Arrays.asList('a', 'b', 'a', 'b', 'c', 'b', 'a', 'c', 'a', 'd', 'e', 'f', 'e', 'g', 'd', 'e', 'h', 'i', 'j', 'h', 'k', 'l', 'i', 'j');
        Assert.assertEquals(Arrays.asList(9, 7, 8), partitionLabels(inputList));
        List<Character> inputList2 = Arrays.asList('a', 'b', 'c');
        Assert.assertEquals(Arrays.asList(1, 1, 1), partitionLabels(inputList2));
        List<Character> inputList3 = Arrays.asList('a', 'b', 'c', 'a');
        Assert.assertEquals(Arrays.asList(4), partitionLabels(inputList3));
    }
}
