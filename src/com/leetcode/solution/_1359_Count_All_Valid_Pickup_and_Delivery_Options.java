package com.leetcode.solution;

/*
 * 在 (P1 D1) 的基础上，我们随便把 P2 和 D2 插空进去（当然，要保持 P2 在 D2 的前面）
 * 所以是四个位置（P1 D1 P2 D2一共四个点）里选两个位置（保持P2 D2）

 * F[1]=1
 * F[2]=1*C(2,4)=6
 * F[3]=F[2]*C(2,6)=90 ,
 * F[4]=F[3]*C(2,8)=2520,
 * F[n]=F[n-1]*C(2,2n)
 * =F[n-1]*n*(2*n-1).
 */

public class _1359_Count_All_Valid_Pickup_and_Delivery_Options {
    int mod = 1000000007; // 不能写作10^7因为次方是pow(a,b), ^是异或

    public int countOrders(int n) {
        long last = 1;
        for (int i = 1; i <= n; i++) {
            //组合 C(2,2*i)
            int c = i * (2 * i - 1);
            last = (last * c) % mod;
        }
        return (int) last;
    }
}
