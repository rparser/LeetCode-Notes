package com.leetcode.solution;

import java.util.*;

class _246_strobogrammatic_number {
    //O(N), O(N) ~O(1)
    public boolean isStrobogrammatic(String num) {
        //建立map存储可能6,9对，9,6对，0,0对，8，8对,1,1对
        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('8', '8');
        map.put('1', '1');
        char[] digits = num.toCharArray();
        // 对比最后一位和第一位
        for (int i = 0; i < (num.length() + 1) / 2; i++) {
            char a = digits[i];
            char b = digits[num.length() - i - 1];
            if (!map.containsKey(a) || map.get(a) != b)
                return false;
        }
        return true;
    }
}