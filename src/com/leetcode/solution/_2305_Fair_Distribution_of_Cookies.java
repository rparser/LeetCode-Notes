package com.leetcode.solution;

import java.util.Arrays;

/**
 * 回溯+3种剪枝
 * 每一个饼干包（一共有n个）可以分给任意k个小朋友，一共有k^n种分法。暴力枚举一遍，找最大饼干数的最小值就行了。
 * n和k最大取值为8，枚举方案一共有8^8个，有点大，暴力可能超时。
 *
 * 那么可以用回溯+剪枝。回溯本身也类似于暴力法，只不过通过适当的剪枝可以将时间复杂度显著降低。
 * 一个技巧+三个剪枝方法：
 * 技巧：一开始对数组进行排序，先发放饼干较多的包，这样可以减少平均回溯深度。
 * 剪枝1：如果剩余的饼干包不够还没有拿到饼干的小朋友分了，直接返回。
 * 剪枝2：如果当前状态下某位小朋友的饼干数量比当前的答案还多，显然继续回溯下去也无法成为最优答案，直接返回。
 * 剪枝3：第一个零食包不管给哪个小朋友，所开启的回溯树都一样，所以首个饼干包只要给第一个小朋友就行了，
 * 这样的回溯树只有一个根节点（一个回溯树），否则有k个回溯树。
 */

public class _2305_Fair_Distribution_of_Cookies {
    int ans = Integer.MAX_VALUE;
    int[] cookies;
    int n;
    int k;

    public int distributeCookies(int[] cookies, int k) {
        //技巧：先发饼干较多的包，这样让回溯过程更快。下面的回溯代码是从最后一个饼干包开始发所以这里是从小到大排序
        Arrays.sort(cookies);

        this.cookies = cookies;
        n = cookies.length;
        this.k = k;

        //启动回溯
        backtrack(new int[k], n - 1);
        return ans;
    }

    //bucket数组存放k个小朋友每个人当前的饼干数量，start为下一个要分发的饼干包下标
    public void backtrack(int[] bucket, int start) {

        //饼干发完了，统计哪个小朋友获得的饼干最多，更新答案。
        if (start < 0) {
            int curAns = Integer.MIN_VALUE;
            for (int count : bucket) {
                curAns = Math.max(curAns, count);
            }
            ans = Math.min(ans, curAns);
            return;
        }

        //剪枝1：如果剩余的饼干包不够空手的小朋友分了，直接返回。
        int zeroCount = 0;
        for (int count : bucket) {
            if (count == 0) zeroCount++;
        }
        if (zeroCount > start + 1) return;

        //剪枝2：如果某位小朋友的饼干数量比当前的答案还多，显然继续回溯下去也无法成为最优答案，直接返回。
        for (int i = 0; i < k; i++) {
            if (bucket[i] > ans) return;
        }

        for (int i = 0; i < k; i++) {
            //剪枝3：第一个零食包不管给哪个小朋友，所开启的回溯树都一样，只要给一个小朋友就行了，这样的回溯树一下子就少了很多。
            if (start == n - 1 && i > 0) return;

            //标准回溯代码
            bucket[i] += cookies[start];
            backtrack(bucket, start - 1);
            bucket[i] -= cookies[start];
        }
        return;
    }
}
