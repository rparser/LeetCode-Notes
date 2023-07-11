package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

public class _616_add_bold_tag_in_string {
    // O(S * N * K) K为单词长度, O(N)
    public String addBoldTag(String str, String[] dict) {
        if (dict.length == 0)
            return str;

        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < str.length(); i++)
            for (String word : dict)
                //遍历每个word，如果某个i startsWith这个word则加入结果
                if (str.startsWith(word, i))
                    // 左闭右闭，右侧也是包含的
                    intervals.add(new int[]{i, i + word.length() - 1});

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int index = 0;
        while (i < intervals.size()) {
            int left = intervals.get(i)[0];
            int right = intervals.get(i)[1];
            //(str,start,end)先把前半段加入,left不包括因为右开
            sb.append(str, index, left);
            //合并区间,当前右大于最近的左，说明有overlap，需要merge
            // -1是因为,因为上面是左闭右闭，所以比如[4,7]下个是[8,10]也需要merge
            while (i < intervals.size() && right >= intervals.get(i)[0] - 1) {
                right = Math.max(right, intervals.get(i)[1]);
                i++;
            }
            // 加上区间内的值
            sb.append("<b>").append(str, left, right + 1).append("</b>");
            index = right + 1;
        }

        if (index <= str.length() - 1)
            sb.append(str.substring(index));

        return sb.toString();
    }
}
