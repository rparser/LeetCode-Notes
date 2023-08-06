package com.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3sum等于0, 016 3sum接近K
 * <p>
 * Time complexity: O(nlogn + n^2)
 * Space complexity: O(1)
 */

public class _015_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); //先排序

        for (int i = 0; i < nums.length - 2; i++) { // i最多nums.length-3，因为left,right在i右侧
            if (i > 0 && nums[i] == nums[i - 1])
                continue;  // 如果nums[i]是同一个值则跳过

            int left = i + 1, right = nums.length - 1; // left从下一位开始,right从最后一位开始
            while (left < right) { // left >=right时才会跳出循环(失败条件)
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // 如果nums[left]是同一个值则跳过
                    while (left < right && nums[left] == nums[left - 1])
                        left++;
                    // 如果nums[right]是同一个值则跳过
                    while (left < right && nums[right] == nums[right + 1])
                        right--;
                    // 超过target太大了, right要左移
                } else if (sum >0)
                    right--;
                else left++;
            }
        }
        return result;
    }
}
