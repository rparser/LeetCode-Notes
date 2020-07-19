package com.leetcode.solution;

import java.util.*;

class _528_random_pick_with_weight {
    // O(N)presum, O(logN)pick, O(N)
    List<Integer> preSum = new ArrayList<>();
    int sum = 0;
    Random rand = new Random();

    public _528_random_pick_with_weight(int[] w) {
        //前缀和区间
        for (int x : w) {
            sum += x;
            preSum.add(sum);
        }
    }

    public int pickIndex() {
        // 0 到总和之前选去数字
        int randomTarget = rand.nextInt(sum);

        int lo = 0;
        int hi = preSum.size() - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (randomTarget >= preSum.get(mid))
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }
}
