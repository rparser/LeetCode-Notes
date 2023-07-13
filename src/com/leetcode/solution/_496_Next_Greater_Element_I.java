package com.leetcode.solution;

import java.util.HashMap;
import java.util.Map;

class _496_Next_Greater_Element_I {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] results = new int[nums1.length];
        int index = 0;
        int j = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }

        for (int k : nums1) {
            int inDex = map.get(k);  //找出nums1元素在nums2中的位置，然后向右方向判断
            for (j = inDex + 1; j < nums2.length; j++) {
                if (k < nums2[j]) {
                    results[index++] = nums2[j];
                    break;
                }
            }
            if (j >= nums2.length) results[index++] = -1;
        }
        return results;
    }
}
