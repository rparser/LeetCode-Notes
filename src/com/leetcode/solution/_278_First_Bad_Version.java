package com.leetcode.solution;


/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class _278_First_Bad_Version {
    // O(logN), O(1)
    public int firstBadVersion(int n) {
        int low = 1, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            //如果版本没有问题，去后面找
            if (!isBadVersion(mid))
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    private boolean isBadVersion(int mid) {
        return true;
    }
}
