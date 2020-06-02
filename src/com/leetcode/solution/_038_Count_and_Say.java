package com.leetcode.solution;

/**
 * 思路： DP问题，举例发现i+1的string就是根据i的string来count & say。
 * 3： 21 就是根据（2:11） 数，有2个1。
 * 建个for loop从1加到n，每个数都call helper func, helper来track下一个不同的char，建sb
 * 例子： https://discuss.leetcode.com/topic/2264/examples-of-nth-sequence/7
 * Complexity: Time O(MN) -  helper run Str len from: 1,2,  4...
 * Space O(N) -  make copy of entire string
 */

public class _038_CountandSay {
    public String countAndSay(int n) {
        String res = "1";
        for (int i = 1; i < n; i++) res = helper(res);
        return res;
    }

    public String helper(String res) {
        StringBuilder sb = new StringBuilder();
        char last = res.charAt(0);
        int count = 1;
        for (int i = 1; i < res.length(); i++) {
            if (res.charAt(i) == last) count++;
            else {
                sb.append(count);
                sb.append(last);
                last = res.charAt(i);
                count = 1;
            }
        }
        sb.append(count);
        sb.append(last);
        return sb.toString();
    }
}
