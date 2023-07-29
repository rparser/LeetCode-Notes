package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.*;

public class _11_GetMinimumCost {

    public static long getMinimumCost(int[] a, int[] b, int m) {
        int n = a.length;
        long totalCost = 0;

        // 使用堆来存储所有商品，按照价格从低到高排序
        PriorityQueue<Item> minHeap = new PriorityQueue<>((x, y) -> Integer.compare(x.cost, y.cost));
        for (int i = 0; i < n; i++) {
            minHeap.add(new Item(a[i], b[i], 1)); // 初始购买第一个商品
        }

        while (m > 0) {
            Item item = minHeap.poll(); // 取出价格最低的商品
            totalCost += item.cost; // 更新总花费
            m--;
            item.j++; // 更新j
            item.cost = item.a + (item.j - 1) * item.b; // 计算下一个购买的商品的价格
            minHeap.add(item); // 将更新后的商品重新放回堆中
        }

        return totalCost;
    }

    static class Item {
        int a; // 商品a数组中的元素
        int b; // 商品b数组中的元素
        int j; // 商品编号
        int cost; // 商品的价格

        public Item(int a, int b, int j) {
            this.a = a;
            this.b = b;
            this.j = j;
            this.cost = a + (j - 1) * b;
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 1};
        int[] b = {1, 2, 3};
        int m = 4;
        long result = getMinimumCost(a, b, m); //7
        System.out.println("最小花费：" + result);
        int[] a2 = {1,3,2};
        int[] b2 = {2,1,3};
        int m2 = 5;
        long result2 = getMinimumCost(a2, b2, m2); //13
        System.out.println("最小花费：" + result2);
        int[] a3 = {2, 1, 1};
        int[] b3 = {1, 2, 3};
        int m3 = 4;
        long result3 = getMinimumCost(a3, b3, m3);
        System.out.println("最小花费：" + result3);
    }
}
