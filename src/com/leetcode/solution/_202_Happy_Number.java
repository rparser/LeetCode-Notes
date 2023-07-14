package com.leetcode.solution;

public class _202_Happy_Number {
    //快慢指针 时间O(logN) 空间O(1)
    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        // 如果fast不是1 且fast slow不等，进入while如果最后相等且不是1则不是快乐数
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    // 数学法
    public boolean isHappy2(int n) {
        int[] mark = new int[8];
        while (n > 1) {
            n = convert(n);
            if (n < 243) {
                int sec = n >> 5;
                int mask = 1 << (n & 0x1f);
                if ((mark[sec] & mask) > 0)
                    return false;

                mark[sec] |= mask;
            }
        }
        return true;
    }

    private int convert(int n) {
        int sum = 0;
        while (n > 0) {
            int t = n % 10;
            sum += t * t;
            n /= 10;
        }
        return sum;
    }
}
