package com.leetcode.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class dpDict {
    //    public int dpDict(int[] dict) {
//        int m = dict.length;
//        int[][] dp = new int[m + 1][n + 1]; //考虑可能长度为零，需要+1
//        for (int i = 0; i <= m; i++) {
//            for (int j = 0; j <= n; j++) {
//                if (i == 0 || j == 0) dp[i][j] = 0;
//                else {
//                    dp[i][j] = (word1.charAt(i - 1) == word2.charAt(j - 1)) ?
//                            dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
//                }
//            }
//        }
//        int val = dp[m][n]; //最长子串长度
//        return dp[0][n]; //长度和减去两个最长子串，即为所需的步数
    public static void main(String[] args) {
        List<int[]> test = new ArrayList<>();
        int[] arr = new int[]{0, 2, 4};
        test.add(arr);
        int[] c = test.get(test.size() - 1);
        c[0] = 100;
        System.out.println(test.get(test.size() - 1)[0] + "   " + test.get(test.size() - 1)[1]);

        List<Integer> test2 = new ArrayList<>();
        int arr2 = 0;
        test2.add(arr2);
        int c2 = test2.get(test2.size() - 1);
        c2 = 101;
        System.out.println(test2.get(test2.size() - 1));

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(10, 1);
        map.put(9, 0);
        map.put(12, 2);
        map.put(11, 0);
        Map<Integer, Integer> collect = map.entrySet().stream()
                .filter(x -> x.getValue() > 0)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        System.out.println(collect);
    }
}
