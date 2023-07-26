package com.leetcode.doordash;

/*
 * bool knows(a, b) 获取到 A 是否认识 B
 * 名人： 其他所有 n - 1 个人都认识他/她，而他/她并不认识其他任何人/ president
 * 双指针，从0开始，假设i=0的人，不认识别的任何人，只能证明他可能是名人，还需要再验证别人都认识他
 * O(N), O(1)
 */

public class _277_寻找名人 {
    public int findCelebrity(int n) {
        int candidate = 0;
        // 最多只有一个名人 - 下面验证i是不是名人
        // 如果 knows(i,j) 为true，说明i不可能是名人(名人不认识其他人),i变成j,
        // 如果 knows(i,j) 为false， 说明j不可能是名人（名人一定被认识）,j++
        // 所以每次调用knows都能排除一个人
        // i出现不认识的j时会 i停止前进,j继续前进
        // 但如果i后有真的名人j出现(i又认识这个j了)，会更新到j
        // 所以i是最可能像名人的人
        for (int j = 1; j < n; j++)
            if (knows(candidate, j))
                candidate = j;
        // i可能是名人因为i不认识别人

        // 要从0开始再过一遍，因为从0到candidate - i 之前的范围没有检查 - isCelebrity方法
        for (int k = 0; k < n; k++) {
            if (k == candidate)
                continue;
            // 如果有人认识他或他认识别人，则他不是名人
            if (knows(candidate, k) || !knows(k, candidate))
                return -1;
        }
        return candidate;
    }

    boolean knows(int a, int b) {
        return true;
    }
}
