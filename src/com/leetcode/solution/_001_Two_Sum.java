package com.leetcode.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * 求数组里两个数的和为某个固定值
 * <p>
 * 1.建立map
 * 2.以此在map加入数字，并查找补值，如果有补值则返回
 * 3.到结束依然没有则throw IllegalArgumentException
 * <p>
 * 要注意一次遍历即可，先查询，查询不到再加入map，可以保证不查询到自己
 */

public class _001_Two_Sum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; //以此在map加入数字，并查找补值，如果有补值则返回
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            } // 找到则返回，否则加入map

            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    // 如果有4种方式到60，就有C-4-2种方式到120,C-4-3种方式到180
    public static int countTwoSumWays(int[] nums, int target) {
        Map<Integer, Integer> numFrequency = new HashMap<>();
        int count = 0;

        for (int num : nums) {
            int complement = target - num;
            if (numFrequency.containsKey(complement)) {
                count += numFrequency.get(complement);
            }
            numFrequency.put(num, numFrequency.getOrDefault(num, 0) + 1);
        }

        return count;
    }

    //当有2-3个30时 不能无重复生成120，4个30可以无重复生成3组不一样的(AB,CD - AC,BD -AD,BC)120
    //5个30可以生成(5x3 - 排除有五种选择，剩下四个能组成3种如上)
    //6个30可以生成(C62 x 3)

    //1个20，1个40 -> 1种， 2个20，2个40->2种，3个20，3个40->(3x2 =6)种(3!)
    //1个20，2个40->1种，2个20，5个40->(5x4=20种)(P多,少)

    public static int findTwoSumCombinations(int[] nums, int target) {
        Map<Integer, Integer> frequency = new HashMap<>();
        int count = 0;

        for (int num : nums) {
            int complement = target - num;
            if (frequency.containsKey(complement) && frequency.get(complement) > 0) {
                count++;
                frequency.put(complement, frequency.get(complement) - 1);
            }
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        return count;
    }


    public static void main(String[] args) {
        int[] nums = {20,30,30,40,30};
        int target = 60;
        int ways = countTwoSumWays(nums, target);
        int ways2 = findTwoSumCombinations(nums, target);
        System.out.println("Number of ways to achieve Two Sum: " + ways);
        System.out.println("组合个数: " + ways2);
    }
}

/**
 * 167 排序数组
 * 170 设计
 * 653 如果是BST
 * 1099 如果小于K
 * 015 三数之和
 */
