package com.leetcode.solution;

class _670_maximum_swap {
    // O(n), O(1)
    // 从左向右扫描，找到某个数字x右侧比他x大最多的数字y,如果有很多y选最后一个y
    // 1788，换最右的8也就是8781
    public int maximumSwap(int num) {
        char[] charsArray = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < charsArray.length; i++)
            last[charsArray[i] - '0'] = i;

        for (int i = 0; i < charsArray.length; i++)
            for (int d = 9; d > charsArray[i] - '0'; d--)
                if (last[d] > i) {
                    char tmp = charsArray[i];
                    charsArray[i] = charsArray[last[d]];
                    charsArray[last[d]] = tmp;
                    return Integer.parseInt(new String(charsArray));
                }

        return num;
    }
}