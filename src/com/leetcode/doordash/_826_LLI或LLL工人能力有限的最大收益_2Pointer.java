package com.leetcode.doordash;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 换个逻辑就是difficulty - price, profit - calories, worker - budget
 * Time Complexity: O(NlogN+QlogQ), where N is the number of jobs, and Q is the number of people.
 * <p>
 * Space Complexity: O(N), the additional space used by jobs.
 *
 */
public class _826_LLI或LLL工人能力有限的最大收益_2Pointer {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int N = difficulty.length;
        Point[] jobs = new Point[N];
        for (int i = 0; i < N; i++) {
            jobs[i] = new Point(difficulty[i], profit[i]);
        }
        Arrays.sort(jobs, Comparator.comparingInt(a -> a.x));
        Arrays.sort(worker); // 工人和工作分别升序排序

        int result = 0, i = 0, bestProfitForThisWorker = 0;
        // bestProfitForThisWorker一定升序
        for (int skill : worker) {
            while (i < N && skill >= jobs[i].x) {
                bestProfitForThisWorker = Math.max(bestProfitForThisWorker, jobs[i].y);
                i++;
            }
            result += bestProfitForThisWorker;
        }
        return result;
    }

    public int maxProfitAssignment2(int[] prices, int[] calories, int[] budgets) {
        int N = prices.length;
        //import java.awt.*;
        Point[] items = new Point[N];
        for (int i = 0; i < N; i++) {
            items[i] = new Point(prices[i], calories[i]);
        }
        Arrays.sort(items, Comparator.comparingInt(a -> a.x));
        Arrays.sort(budgets); // 工人和工作分别升序排序

        int result = 0, i = 0, bestCaloriesForThisWorker = 0;
        // bestProfitForThisWorker一定升序
        for (int budget : budgets) {
            while (i < N && budget >= items[i].x) {
                bestCaloriesForThisWorker = Math.max(bestCaloriesForThisWorker, items[i].y);
                i++;
            }
            result += bestCaloriesForThisWorker;
        }
        return result;
    }

    /**
     * Knapsack problem - O(MN) O(MN)
     */
    public int maxCalories(List<Integer> prices, List<Integer> calories, int budget) {
        int n = prices.size();
        // 二维数组,代表考虑前i个产品，预算为j的情况下，多一位是第一行第一列为0
        // 结论就是最右下的数字
        int[][] dp = new int[n + 1][budget + 1];

        // Initialize the DP table with 0 calories for 0 budget and vice versa
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= budget; j++) {
            dp[0][j] = 0;
        }

        // Fill the DP table using dynamic programming
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= budget; j++) {
                // 如果当前价格prices.get(i - 1)已经大于了当前的budget j，那这个值就是上一个值（同一列）
                if (prices.get(i - 1) > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 如果这个prices.get(i - 1)有可能能放进去 那么
                    // 要么是1不放进去（和上面的的if一样），dp[i - 1][j]
                    // 要么是2放进去，dp[i - 1][j - prices.get(i - 1)]除去当前价格的剩余预算的最大值 + calories.get(i - 1)当前的价值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - prices.get(i - 1)] + calories.get(i - 1));
                }
            }
        }
        // The maximum calories can be found in the bottom-right cell of the DP table
        return dp[n][budget];
    }

    public static void main(String[] args) {
//        MaxCaloriesCalculator calculator = new MaxCaloriesCalculator();
//        List<Integer> prices = List.of(2, 3, 4, 5); // Example prices list
//        List<Integer> calories = List.of(100, 200, 300, 400); // Example calories list
//        int budget = 8; // Example budget
//        int maxCalories = calculator.maxCalories(prices, calories, budget);
//        System.out.println("Maximum calories within the budget: " + maxCalories);
    }
}
