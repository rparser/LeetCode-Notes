package com.leetcode.solution;

class _997_Find_the_Town_Judge {
    public int findJudge(int N, int[][] trust) {
        // 两个数组保存结果
        int[] trustGive = new int[N];
        int[] trustReceive = new int[N];
        for (int[] person : trust) {
            trustGive[person[0] - 1]++;
            trustReceive[person[1] - 1]++;
        }
        for (int i = 0; i < N; i++)
            if (trustGive[i] == 0 && trustReceive[i] == N - 1) return i + 1;
        return -1;
    }
}
