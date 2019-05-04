package com.leetcode.solution;

import java.util.*;

public class _0163SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0]+nums[1]+nums[2];
        for(int i=0; i<nums.length; i++)
        {
            int left = i+1;
            int right = nums.length-1;
            while(left<right)
            {
                int sum = nums[left]+nums[right]+nums[i];
                if(Math.abs(sum-target)<Math.abs(result-target))
                    result = sum;

                if(sum>target)
                    right--;
                else if(sum<target)
                    left++;
                else
                    return sum;
            }
        }
        return result;
    }
}
