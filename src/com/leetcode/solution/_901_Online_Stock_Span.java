package com.leetcode.solution;

/**
 * 第二种方式，我们采用两个数组，分别是prices用来记录股票价格和days用来记录跨度天数。那么针对于第n次输入的股票，
 * 它的价格和跨度天数就是prices[n]和days[n]。
 *
 * 除了prices和days这两个数组之外，我们还需要两个指针，分别是index指针，
 * 用来指向“待输入股票”；p指针，index指针的前一个指针，用来与“待输入股票”进行price对比用的，
 * 如果它的price小于等于“待输入股票”的price，p就会向前移动。
 *
 * 关于p向前移动还有一点需要注意的就是，p向前移动格子的数量，就是days的具体值；
 * 当days等于1时，就向前移动1个格子；如果days等于2时，就向前移动2个格子（因为days等于2，说明已经是两个格子聚合过的值了，就不需要重复统计了）。
 */
public class _901_Online_Stock_Span {
    int index = 0; // index：待插入的位置
    int[] prices, days; // 价格列表和跨度天数列表，同一下标，一一对应关系

    public _901_Online_Stock_Span() {
        prices = new int[10000];
        days = new int[10000];
        index = 0;
    }

    public int next(int price) {
        int p = index - 1; // 待对比的位置
        while (p >= 0 && prices[p] <= price) {
            p -= days[p];
        } // 向前移动p
        prices[index] = price;
        days[index] = index - p;
        return days[index++]; // index加1
    }
}
