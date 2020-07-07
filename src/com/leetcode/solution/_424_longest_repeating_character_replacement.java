package com.leetcode.solution;

class _424_longest_repeating_character_replacement {
    public int characterReplacement(String s, int k) {
        int[] cCount = new int[26];
        // 窗口内出现次数最多的字符的出现次数
        int maxCount = 0;
        char[] cArray = s.toCharArray();

        int result = 0;
        // right一直向右扩充
        for (int left = 0, right = 0; right < cArray.length; right++) {
            // right move一格
            cCount[cArray[right] - 'A']++;
            // 每扩充一格 就重新计算maxCount
            maxCount = Math.max(maxCount, cCount[cArray[right] - 'A']);

            // 如果可改变的字符 <=k 更新结果
            if (right - left + 1 - maxCount <= k)
                result = Math.max(right - left + 1, result);
            else {
                //否则窗口最左面字母的count--
                cCount[cArray[left] - 'A']--;
                // 如果最左边的字母是最多的字母，则maxCount需要-
                if (cCount[cArray[left] - 'A'] == maxCount)
                    maxCount--;

                left++;
            }
        }
        return result;
    }
}