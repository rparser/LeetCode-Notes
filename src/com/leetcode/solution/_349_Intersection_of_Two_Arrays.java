package com.leetcode.solution;

import java.util.*;

/**
 * T: O(n)
 */

public class _349_Intersection_of_Two_Arrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> res = new ArrayList<>();
        //Add all elements to set from array 1
        for (int i : nums1)
            set.add(i);

        for (int j : nums2)
            // If present in array 2 then add to res and remove from set
            if (set.contains(j)) {
                res.add(j);
                set.remove(j);
            }

        // Convert ArrayList to array
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++)
            arr[i] = res.get(i);

        return arr;
    }
}
