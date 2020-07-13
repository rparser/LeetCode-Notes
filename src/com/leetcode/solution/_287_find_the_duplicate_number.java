package com.leetcode.solution;

public class _287_find_the_duplicate_number {
    // Cyclic sort - 需要改动数组
    // n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n）
    // O(N), O(1),快慢指针在下面，无须改动但需要遍历两次
    public int findDuplicate(int[] nums) {
        int i = 0;
        while (i < nums.length)
            // 如果数字不对
            if (nums[i] != i + 1) {
                //
                if (nums[i] != nums[nums[i] - 1])
                    swap(nums, i, nums[i] - 1);
                else //另一个数字也对则找到了重复数
                    return nums[i];
                //数字对了则不交换
            } else
                i++;

        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //快慢指针 - 不用动数组 肯定有cycle
    // fast走两步，找到了则必有cycle,环的入口是重复数
    public int findDuplicateFastSlowPointer(int[] nums) {
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        //起点到环的入口长度为m，环的周长为c，在fast和slow相遇时slow走了n步。
        // 则fast走了2n步，fast比slow多走了n步，而这n步全用在了在环里循环（n%c==0）
        //fast 和 slow 相遇时，slow 在环中行进的距离是n-m，其中 n%c==0。
        // 这时我们再让 slow 前进 m 步——也就是在环中走了 n 步了。
        // 而 n%c==0 即 slow 在环里面走的距离是环的周长的整数倍，就回到了环的入口了，而入口就是重复的数字。
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // 二次遍历
        int pre1 = 0;
        int pre2 = slow;
        while (pre1 != pre2) {
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }

    public int findDuplicateOld(int[] nums) {
        int len = nums.length;
        int left = 1;
        int right = len - 1;
        while (left < right) {
            // 在 Java 里可以这么用，当 left + right 溢出的时候，无符号右移保证结果依然正确
            int mid = (left + right) >>> 1;

            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt += 1;
                }
            }
            // 根据抽屉原理，小于等于 4 的个数如果严格大于 4 个
            // 此时重复元素一定出现在 [1, 4] 区间里

            if (cnt > mid) {
                // 重复元素位于区间 [left, mid]
                right = mid;
            } else {
                // if 分析正确了以后，else 搜索的区间就是 if 的反面
                // [mid + 1, right]
                left = mid + 1;
            }
        }
        return left;
    }
}
