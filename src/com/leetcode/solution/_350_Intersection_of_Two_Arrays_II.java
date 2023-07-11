package com.leetcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class _350_Intersection_of_Two_Arrays_II {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1)
            map.put(i, map.getOrDefault(i, 0) + 1);

        for (int j : nums2)
            if (map.getOrDefault(j, 0) > 0) {
                list.add(j);
                map.put(j, map.get(j) - 1);
            }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            result[i] = list.get(i);

        return result;
    }
}