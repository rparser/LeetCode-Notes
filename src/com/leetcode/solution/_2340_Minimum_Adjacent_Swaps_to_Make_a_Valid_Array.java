package com.leetcode.solution;


// Time complexity = O(n)
// Space complexity = O(1)

/**
 * To get the minium number of swaps, we need to:
 * <p>
 * find the index of the largest number;
 * in case of duplicates, we should find the rightmost largest so less swaps -- rindex
 * find the index of the smallest number;
 * in case of duplicates, we should find the leftmost smallest so less swaps -- lindex
 * <p>
 * Now the number of swaps is really the distance between the indexes we found above to
 * either ends based on the requirements.
 * We should be aware that, if lindex > rindex (the smallest number is on the right side of the largest number),
 * we need to do one less swap operation because swapping one of the largest/smallest number
 * to its destination would swap the other. For example, [2,1] only needs one swap operation, not two.
 */
public class _2340_Minimum_Adjacent_Swaps_to_Make_a_Valid_Array {
    public int minimumSwaps(int[] nums) {
        int max = -1, min = Integer.MAX_VALUE, maxIndex = 0, minIndex = 0;
        // 更新max和min的index
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
                minIndex = i;
            }
            if (nums[i] >= max) {
                max = nums[i];
                maxIndex = i;
            }
        }

        // If this is true, then we need to swap places with the max number and min number
        // which means that the min number will need one less swap to reach the start of the array
        // 如果这种出现 可以少swap一次 因为重复计算了
        if (minIndex > maxIndex) {
            minIndex--;
        }

        return (nums.length - 1 - maxIndex) + (minIndex - 0);
    }
}
