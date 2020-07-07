package com.leetcode.solution;

public class _041_FirstMissingPositive {
    //交换法 O(N) ~ O(1)
    public int firstMissingPositive2(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++)
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i])
                // 如果数值在范围内，而且对面需要swap的数据并不是正确数值（有机会到应该的位置），才交换
                // 已经是正确值则不动
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);

        // [1, -1, 3, 4]
        for (int i = 0; i < len; i++)
            if (nums[i] != i + 1) //返回第一个不对的数字
                return i + 1;

        // 都正确则返回数组长度 + 1
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
    // O(N) ~ O(1)
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++)
            if (nums[i] <= 0 || nums[i] > n)
                nums[i] = n + 1; //把负数和过大的数（无关的）变成n+1，所以所有数会在1...n+1之间

        // 2. mark each cell appearing in the array, by converting the index for that number to negative
        for (int i = 0; i < n; i++) {
            int cur = Math.abs(nums[i]); //找到绝对值
            if (cur > n) continue; //超出范围则跳过

            cur--; // 因为数组从0开始所以需要-1，否则比如[1, 2]会溢出
            if (nums[cur] > 0) nums[cur] = -1 * nums[cur]; //范围内的正数变负数
        }

        // 3. 找到第一是不是负数的 (所以就不在数组里)
        for (int i = 0; i < n; i++)
            if (nums[i] >= 0) return i + 1;

        // 都正确则返回数组长度 + 1
        return n + 1;
    }
}