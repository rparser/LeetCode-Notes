package com.leetcode.solution;//三个循环体：
//        1.计算目标数组每个字母的个数
//        2.根据reference开始循环输出
//        3.最后添加reference中没有出现的字母

class _791_custom_sort_string {
    // S是规则，T是输出 O(S+T),O(T)
    public String customSortString(String S, String T) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];
        // 统计T中单词的个数
        for (char i : T.toCharArray())
            count[i - 'a']++;
        //S规则里有的，按照S的字母c顺序加入
        for (char c : S.toCharArray()) {
            for (int j = 0; j < count[c - 'a']; j++)
                sb.append(c);

            count[c - 'a'] = 0;
        }

        // S里没有的还剩余的依次加入
        for (char c = 'a'; c <= 'z'; c++)
            for (int i = 0; i < count[c - 'a']; i++)
                sb.append(c);

        return sb.toString();
    }
}
