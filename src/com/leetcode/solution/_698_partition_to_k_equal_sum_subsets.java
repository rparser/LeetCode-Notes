package com.leetcode.solution;

import java.util.*;

public class _698_partition_to_k_equal_sum_subsets {
    enum Result { TRUE, FALSE }
    //nums下标、剩余的nums的和、memo数组、nums数组、每组的和
    // used 是整形、有32位， 而nums长度最长是16,所以used完全够用。used某一位是1代表nums[某一位]被用过
    boolean search(int used, int todo, Result[] memo, int[] nums, int target) {
        if (memo[used] == null) {
            memo[used] = Result.FALSE;
            // 防止targ为0,所以求余之前每次减掉1、求余之后再加1,
            int targ = (todo - 1) % target + 1;
            // 先尝试放第1个数、如果成功就结束，如果不成功、就再尝试放第2个数，依次类推、直到最后一个数
            for (int i = 0; i < nums.length; i++)
                //这个位置的数没有用过、然后不大于target，就可用
                if ((((used >> i) & 1) == 0) && nums[i] <= targ)
                    if (search(used | (1<<i), todo - nums[i], memo, nums, target)) {
                        memo[used] = Result.TRUE;
                        break;
                    }
        }
        return memo[used] == Result.TRUE;
    }
    public boolean canPartitionKSubsets(int[] nums, int k) {
        //nums数组的总和
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;

        // memo长度是2的nums.length次方
        Result[] memo = new Result[1 << nums.length];
        // 最后一个位置设为true
        memo[(1 << nums.length) - 1] = Result.TRUE;
        return search(0, sum, memo, nums, sum / k);
    }
}
