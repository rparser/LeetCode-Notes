package com.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 当n大于2的时候，都是将n等于2的结果，
// 中间夹上n-2时的结果，这样就可以使用递归了,其中递归的中间值，可以使用List来保存。

class _247_strobogrammatic_number_ii {
    // Recursive O(5 * N /2 ) ~ O(N), O(N * 个数)
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    // nLeft是需要求的字符串长度； n表示题目中要求的字符串长度
    public List<String> helper(int nLeft, int n) {
        // 第一步：判断递归是否应当结束
        if (nLeft == 0)
            return new ArrayList<>(Arrays.asList(""));
        if (nLeft == 1)
            return new ArrayList<>(Arrays.asList("0", "1", "8"));

        // 第二步：缩小问题规模
        List<String> list = helper(nLeft - 2, n);

        // 第三步：整合结果
        List<String> res = new ArrayList<>();
        for (String s : list) {
            // n=m时，表示最外层处理,比如需求n=m=2, '00'不合法
            if (nLeft != n) {
                res.add("0" + s + "0");
            }
            // 其他情况直接加上
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        return res;
    }
}