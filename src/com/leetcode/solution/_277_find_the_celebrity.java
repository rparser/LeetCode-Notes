package com.leetcode.solution;

public class _277_find_the_celebrity {
    //bool knows(a, b) 获取到 A 是否认识 B
    // 名人： 其他所有 n - 1 个人都认识他/她，而他/她并不认识其他任何人/ president
    public int findCelebrity(int n) {
        int i = 0;
        //最多只有一个名人
        //如果 knows(i,j) 为true，说明i不可能是名人,i变成j,
        //如果 knows(i,j) 为false， 说明j不可能是名人,j++
        //i出现不认识时会停止前进,但如果i后有真的名人j出现，会更新到j
        //所以i是最可能像名人的人
        for (int j = 1; j < n; j++)
            if (knows(i, j))
                i = j;
        //i可能是名人因为i不认识
        for (int k = 0; k < n; k++) {
            if (k == i)
                continue;
            // 如果有人认识他或他认识别人，则他不是名人
            if (knows(i, k) || !knows(k, i))
                return -1;
        }
        return i;
    }

    boolean knows(int a, int b) {
        return true;
    }
}