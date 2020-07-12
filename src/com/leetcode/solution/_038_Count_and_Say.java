package com.leetcode.solution;

/**
 * 思路： DP问题，举例发现i+1的string就是根据i的string来count & say。
 * 3： 21 就是根据（2:11） 数，有2个1。
 * 建个for loop从1加到n，每个数都call helper func, helper来track下一个不同的char，建sb
 * 例子： https://discuss.leetcode.com/topic/2264/examples-of-nth-sequence/7
 * Complexity: Time O(MN) -  helper run Str len from: 1,2,  4...
 * Space O(N) -  make copy of entire string
 */

public class _038_Count_and_Say {
    //Time O(MN),O(N)
    public String countAndSayRecursive(int n) {
        StringBuilder sb = new StringBuilder();
        int p1 = 0;
        int cur = 0;
        if (n == 1)
            return "1";

        String str = countAndSayRecursive(n - 1);
        for (cur = 1; cur < str.length(); cur++) {
            if (str.charAt(p1) != str.charAt(cur)) {// 如果碰到当前字符与前面紧邻的字符不等则更新此次结果
                int count = cur - p1;
                sb.append(count).append(str.charAt(p1));
                p1 = cur;
            }
        }
        // 离开loop再统计一次
        int count = cur - p1;
        sb.append(count).append(str.charAt(p1));
        return sb.toString();
    }

    public static String countAndSayIterative(int n) {
        if (n == 1) return "1";
        String result = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int pre = 0;// 前驱节点
            int j = 0;
            for (j = 0; j < result.length(); j++) {
                if (result.charAt(j) != result.charAt(pre)) {
                    sb.append(j - pre).append(result.charAt(pre));
                    pre = j;
                }
            }
            sb.append(j - pre).append(result.charAt(pre));
            result = sb.toString();
        }
        return result;
    }
}
