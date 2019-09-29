package com.leetcode.solution;

public class _041_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++)
            if (nums[i] <= 0 || nums[i] > n)
                nums[i] = n + 1; //把负数和过大的数（无关的）变成n+1，所以所有数会在1...n+1之间

        // 2. mark each cell appearing in the array, by converting the index for that number to negative
        for (int i = 0; i < n; i++) {
            int cur = Math.abs(nums[i]); //找到绝对值
            System.out.println(nums[i]+"ccc");
            if (cur > n) continue; //超出范围则跳过

            cur--; // -1 for zero index based array (so the number 1 will be at pos 0)
            if (nums[cur] > 0) nums[cur] = -1 * nums[cur]; //范围内的正数变负数
            System.out.println("nums[cur]"+nums[cur]);
        }

        // 3. find the first cell which isn't negative (doesn't appear in the array)
        for (int i = 0; i < n; i++)
            if (nums[i] >= 0) return i + 1;

        // 4. no positive numbers were found, which means the array contains all numbers 1..n
        return n + 1;
    }
}