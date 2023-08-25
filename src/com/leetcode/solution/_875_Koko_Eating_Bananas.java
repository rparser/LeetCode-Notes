package com.leetcode.solution;

/**
 * 时间复杂度：O(Nlogmax(piles))，这里 NNN 表示数组 piles 的长度。我们在 [1,max⁡piles][1, \max{piles}][1,maxpiles] 里使用二分查找定位最小速度，而每一次执行判别函数的时间复杂度是 O(N)O(N)O(N)；
 * 空间复杂度：O(1)，算法只使用了常数个临时变量。
 */
public class _875_Koko_Eating_Bananas {
    public int minEatingSpeed(int[] piles, int H) {
        int maxVal = 1;
        for (int pile : piles) {
            maxVal = Math.max(maxVal, pile);
        }

        // 速度最小的时候，耗时最长
        int left = 1;
        // 速度最大的时候，耗时最短
        int right = maxVal;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (calculateSum(piles, mid) > H) {
                // 耗时太多，说明速度太慢了，下一轮搜索区间是 [mid + 1..right]
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 如果返回的小时数严格大于 H，就不符合题意
     *
     * @param piles
     * @param speed
     * @return 需要的小时数
     */
    private int calculateSum(int[] piles, int speed) {
        int sum = 0;
        for (int pile : piles) {
            // 上取整可以这样写
            sum += (pile + speed - 1) / speed;
        }
        return sum;
    }
}
