package com.leetcode.solution;

class _1060_Missing_Element_in_Sorted_Array {
    //O(logN), O(1)
    public int missingElement(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length == 0) throw new IllegalArgumentException("invalid param");

        int min = nums[0];
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 对于以0开头，连续无缺失值数组，索引和数字相等，如果前面有缺失值，则必然num[idx] - idx > 0；
            // 对于缺失超过k个值的数字，必然有num[idx] - idx - k >= 0，由于本题数组开始值不为0，还需要减去最小值
            if (nums[mid] - min - k >= mid) {
                // 1、right设置为mid而不是mid-1，使得right始终保持缺失值超过k的位置
                // 2、该策略的改变也使得循环条件变为left < right
                // 3、考虑到如果整个数组缺失值不超过k，则最后left会停留在数组最后一位，这样会增加判断复杂度，可以设置初始right为数组长度，省去判断步骤
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 最后结果分两步判断：
        // 1、如果整个数组缺失值不超过k，则left为nums.length，假设数组连续，
        // 则最后一位为起始值加数组长度减一，这样大于数组末尾的第k值就是最后一位加k；
        // 2、如果left在数组中，则根据收缩条件num[left] - left - min - k >= 0为最前面满足条件的值，
        // 即num[left - 1] -left + 1 - min - k < 0 ，即min + left + k - 1在left - 1和left索引指向的数的中间，可以推断为缺失的第k个值。结合得到一个公式：
        return min + left + k - 1;
    }
}