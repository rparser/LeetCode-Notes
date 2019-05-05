package com.leetcode.solution;

import java.util.*;

/**
 * 3sum等于0, 016 3sum接近K
 *
 * Time complexity: O(nlogn + n^2)
 * Space complexity: O(1)
 */

public class _015_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); //先排序

        for (int i = 0; i < nums.length - 2; i++) { //从前两个开始
            if (i > 0 && nums[i] == nums[i - 1])
                continue;  // skip same result

            int left = i + 1, right = nums.length - 1; //left从下一位开始,right从最后一位开始
            int target = -nums[i]; // target为当前值的负值
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;  // skip same result
                    while (left < right && nums[right] == nums[right + 1]) right--;  // skip same result
                } else if (nums[left] + nums[right] > target) { // 如果超过target, right要右移
                    right--;
                } else { //不到target,left左移
                    left++;
                }
            }
        }
        return result;
    }
}
