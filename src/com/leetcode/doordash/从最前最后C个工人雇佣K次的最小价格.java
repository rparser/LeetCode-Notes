package com.leetcode.doordash;

import java.util.PriorityQueue;
import java.util.Queue;

class 从最前最后C个工人雇佣K次的最小价格 {
    public long totalCost(int[] costs, int k, int candidates) {
        Queue<Integer> qLeft = new PriorityQueue<>();
        Queue<Integer> qRight = new PriorityQueue<>();
        int left = candidates;
        int n = costs.length;
        int right = Math.max(left - 1, n - candidates - 1);
        // left到right是不属于两个candidates的中间区域。
        // [0 --candidate-- l...... r -- candidate--]
        long ans = 0;
        //初始化两个candidates的queue
        for (int i = 0; i < candidates; i++)
            qLeft.add(costs[i]);
        for (int i = n - 1; i > right; i--)
            qRight.add(costs[i]);

        //开始k次雇佣
        while (k-- > 0) {
            if (!qLeft.isEmpty() && !qRight.isEmpty()) {//q1和q2都有足够的雇佣人数可供挑选
                if (qLeft.peek() <= qRight.peek()) {//选出最小的花费的那一个
                    ans += qLeft.poll();//从q1中雇佣
                    if (left <= right) qLeft.add(costs[left++]);//因为要维护两个queue的长度，少了就要补充
                } else {
                    ans += qRight.poll();//从q2中雇佣
                    if (left <= right) qRight.add(costs[right--]);
                }
            } else if (qRight.isEmpty()) {//如果q2是空的，那么只能从q1雇佣了
                ans += qLeft.poll();//从q1中雇佣
                if (left <= right) qLeft.add(costs[left++]);//因为要维护两个queue的长度，少了就要补充
            } else {//如果q1是空的，那么只能从q2雇佣了
                ans += qRight.poll();//从q2中雇佣
                if (left <= right) qRight.add(costs[right--]);
            }
        }
        return ans;

    }
}
