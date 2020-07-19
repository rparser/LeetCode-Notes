package com.leetcode.solution;

/**
 * O(N+AlogA).
 */

public class _767_Reorganize_String {
    //O(N + logA)), O(A) A是字母表的大小
    public String reorganizeString(String S) {
        //先找出最多的字母
        int[] count = new int[26];
        char maxChar = 'a';
        for (char c : S.toCharArray()) {
            count[c - 'a']++;
            if (count[c - 'a'] > count[maxChar - 'a'])
                maxChar = c;
        }
        // 不能有某个字母超出1/2的数值
        if (count[maxChar - 'a'] > (S.length() + 1) / 2)
            return "";
        //思路：一个字符串大小的数组，先把maxChar从0,2,4,6,8填充，把其他的填10,12,1,3,5,7,9,11的顺序
        //这样不会出现相邻为同字母，因为同字母相邻至少要填充-偶数：len/2或奇数 (len+1)/2 能做到的只有maxChar！
        //第二多的也不可能做到！
        //cur = 0填到哪里了
        char[] result = new char[S.length()];
        int cur = 0;
        while (count[maxChar - 'a'] > 0) {
            result[cur] = maxChar;
            count[maxChar - 'a']--;
            cur += 2;
        }

        for (int i = 0; i < 26; i++)
            while (count[i] > 0) {
                //到最后一位从1(奇数)再开始
                if (cur >= S.length())
                    cur = 1;

                result[cur] = (char) (i + 'a');
                count[i]--;
                cur += 2;
            }

        return new String(result);
    }
}
