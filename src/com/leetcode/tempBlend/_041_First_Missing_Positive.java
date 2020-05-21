package com.leetcode.solution;

public class _041_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        //将数组中存在的小于等于0还有大于等于n的数全部置为不考虑数 MAX_VALUE
        for (int i = 0; i < n; i++)
            if (nums[i] <= 0 || nums[i] > n)
                nums[i] = Integer.MAX_VALUE;
        //遍历数组，将遍历到的数字对应位置的数标为负数
        //例如nums[i] = 3,就把数组第3个数nums[2]标为负数
        for (int i = 0; i < n; i++) {
            //遇到不用考虑数MAX_VALUE直接跳过
            if (Math.abs(nums[i]) == Integer.MAX_VALUE)
                continue;
            //否则就是需要考虑的数
            int cur = Math.abs(nums[i]);//这个数可能被标记过所以要abs
            if (nums[cur - 1] > 0)//consider这个数对应位置没有标负
                nums[cur - 1] = -nums[cur - 1];
        }
        //从头遍历，遇到第i个位置的数为正数，就说明i这个数字没有出现
        for (int i = 0; i < n; i++)
            if (nums[i] > 0)
                return i + 1;
        //如果上述循环结束发现所有数都负了，那么缺少的就是数n+1（n为数组长度）
        return n + 1;
    }

    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);
            }
        }

        // [1, -1, 3, 4]
        for (int i = 0; i < len; i++)
            if (nums[i] != i + 1)
                return i + 1;

        // 都正确则返回数组长度 + 1
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}