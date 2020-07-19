package com.leetcode.solution;

public class _065_Valid_Number {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean hasDot = false;
        boolean hasE = false;
        boolean hasDigit = false;
        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                hasDigit = true;
            } else if (s.charAt(i) == '.') {
                //.点之前不能有E 和 之前的点
                if (hasE || hasDot)
                    return false;
                hasDot = true;
                // E之前 不能有 E 或没有数字
            } else if (s.charAt(i) == 'e') {
                if (hasE || !hasDigit)
                    return false;
                hasDigit = false;
                hasE = true;
                // 加减号之前必须是e
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0 && s.charAt(i - 1) != 'e')
                    return false;
            } else
                return false;
        }
        return hasDigit;
    }

    public boolean isNumberwithoutE(String s) {
        s = s.trim();
        boolean hasDot = false;
        boolean isNumber = false;
        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                isNumber = true;
            } else if (s.charAt(i) == '.') {
                if (hasDot)
                    return false;
                hasDot = true; // first char can be '.' 如果第一个字符不能为.则要加入if判断是否为第一否则返回false
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0)
                    return false;
            } else
                return false;
        }
        return isNumber;
    }
}
