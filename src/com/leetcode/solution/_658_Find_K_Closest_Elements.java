package com.leetcode.solution;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 离x最近的K个点，二分搜索
 * If x - arr[mid] > arr[mid + k] - x,
 * it means arr[mid + 1] ~ arr[mid + k] is better than arr[mid] ~ arr[mid + k - 1],
 * and we have mid smaller than the right i.
 * So assign left = mid + 1.
 */

public class _658_Find_K_Closest_Elements {
    //O(logn+k), O(k) 有序数组
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 找到最左边的left
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (x - arr[mid] > arr[mid + k] - x) { //比较左右距离，如果左侧差更大
                left = mid + 1;
            } //mid右侧更好
            else { //如果右侧差大于等于
                right = mid;
            } //mid左侧更好
        }
//        return result = Arrays.copyOfRange(arr, left, left + k);
        return Arrays.stream(arr, left, left + k).boxed().collect(Collectors.toList());
    }
}
