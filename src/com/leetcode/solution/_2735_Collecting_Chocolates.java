package com.leetcode.solution;

import java.util.Arrays;
import java.util.stream.LongStream;

public class _2735_Collecting_Chocolates {
    public long minCost(int[] nums, int x) {

    /*
        如果不操作，第i个巧克力必须花费nums[i]收集，总成本sum(nums)。
        操作一次，第i个巧克力可以花费nums[i], nums[(i+1)%n]购买，但总是选择最小的成本购买
        操作两次，第i个巧克力可以花费nums[i], nums[(i + 1) % n], nums[(i + 2) % n])购买，但总是选择最小的成本购买
        ...
        操作k次，第i个巧克力可以花费 nums[i], nums[(i + 1) % n],..., nums[(i + k) % n] 购买，但总是选择最小的成本购买

        for example：nums = [20, 1, 15] , x = 5
        如果不操作，总成本为36；
        根据题意，操作一次，购买i位置的巧克力可以用i+1位置上的成本购买，但只有i+1位置上的成本低于i时，才会真正以i+1的成本购买，否则就以i位置的成本购买:
        1. 操作一次，每个位置的巧克力的购买成本可以是[1, 15, 20]， 但是每个位置的最小购买成本变为：[min(20, 1), min(1, 15), min(15, 20)] = [1, 1, 15]，意味着第1个巧克力购买的最小成本为1，第二个巧克力的最小成本还是为1（因为自身的成本就比较小，改变类型后成本变高），第三个巧克力的最小成本还是15，操作成本为x = 5，总成本为1 + 1 + 15 + 5 = 22.
        2. 操作两次（在前一次的基础上再操作一次），每个位置的巧克力的购买成本可以是[15, 20, 1]，但是每个位置的巧克力最小购买成本变为：[min(20, 1, 15), min(1, 15, 20), min(15, 20, 1)] = [1, 1, 1]， 第一、二、三个巧克力的最小成本都为1，操作成本为2 * x = 10，总成本为 1 + 1 + 1 + 10 = 13.
        ...
        3. 操作三次，每个位置的巧克力的购买成本可以是[20, 1, 15], 又变成了初始的购买成本，所以最多操作 n - 1次，n是数组的长度。
    */

        // 当前第i个巧克力的最小购买成本
        long[] minCostArray = Arrays.stream(nums).mapToLong(v -> v).toArray();
        // 当前购买所有巧克力的总成本
        long minCost = LongStream.of(minCostArray).sum();

        for (int i = 1; i < minCostArray.length; i++) {
            // 操作i次后，更新购买每个位置的巧克力的最小成本
            long[] updateMinCostArray = new long[minCostArray.length];
            for (int j = 0; j < minCostArray.length; j++) {
                // 根据题意，i位置上的巧克力可以用i+1位置上的成本购买，但i+1位置的成本需要低于i，否则不如直接买
                updateMinCostArray[j] = Math.min(minCostArray[j], minCostArray[(j + 1) % minCostArray.length]);
            }
            // 计算总成本
            long updateMinCost = LongStream.of(updateMinCostArray).sum() + (long) i * (long) x;
            // 更新每个位置上的巧克力购买的最小成本
            minCostArray = updateMinCostArray;
            minCost = Math.min(minCost, updateMinCost);
        }
        return minCost;

    }
}
