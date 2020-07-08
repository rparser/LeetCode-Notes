package com.leetcode.solution;

class _069_sqrtx {
    //O(logx)，即为二分查找需要的次数,O(1)
    public int mySqrt(int x) {
        if (x < 1)
            return 0;
        else if (x == 1)
            return 1;

        // 定义左指针指向 2 ，定义右指针指向输入值的中间位置。
        int l = 2, r = x / 2;
        while (l <= r) {
            // 找到遍历区间的中间位置。
            int mid = l + (r - l) / 2;
            // 得到中间位置的平方，定义 long 类型防止溢出。
            long num = (long) mid * mid;
            if (num > x)
                r = mid - 1;
            else if (num < x)
                l = mid + 1;
            else
                return mid;
        }
        return r;
    }
//牛顿迭代
//    (1) 建立结果值 k 和 输入值 x 的函数关系，即 k^2 - x = 0 。
//            (2) 当 k 和 x / k 差小于 1 (两数之间没有其它整数)则得到结果值。
//            *** 牛顿迭代法是一种求解方程近似根的方法。
//O(logx)，即为二分查找需要的次数,O(1)
    public int mySqrtNewTon(int x) {
        if (x < 1)
            return 0;
        else if (x == 1)
            return 1;
        // 建立结果值和输入值之间的函数关系。
        double x0 = x;
        double x1 = (x0 + x / x0) / 2.0;
        // 迭代找到最接近的结果值。
        while (Math.abs(x0 - x1) >= 1) {
            x0 = x1;
            x1 = (x0 + x / x0) / 2.0;
        }
        return (int) x1;
    }

}

