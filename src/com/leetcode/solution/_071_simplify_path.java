package com.leetcode.solution;

import java.util.*;

/**
 * 首先定义栈用来存储路径信息，定义字符数组 str 来分隔字符串
 * 依次遍历字符数组内容，这里使用增强型 for 循环，如果是 “..” 还要再判断是否为空才能弹出栈
 * 如果不为空也不为 “.” 这说明当前元素是路径信息，入栈即可
 * 最后遍历完之后，先判断栈中是否有元素，没有则返回 “/”
 * 如果有元素，则使用 StringBuilder 来存放可变字符串，最后返回 ans 即可。
 */
class _071_simplify_path {
    // O(N), O(N)
    public String simplifyPath(String path) {
        // 用linkedlist是因为最后要从队伍的头取出
        Deque<String> stack = new LinkedList<>();
        String[] str = path.split("/");
        for (String s : str) {
            // 如果数组非空,且访问到的是 “..” 则说明要返回上一级,要pop(removeLast)
            if (s.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();

                // 如果数组非空 且 不是 “.”  说明当前是路径信息，要push
            } else if (!s.equals("") && !s.equals("."))
                stack.push(s);
        }
        // 如果栈内没有元素说明没有路径信息，返回 “/” 即可
        if (stack.isEmpty())
            return "/";

        // sb用"/"间隔,从队伍的头取出
        StringBuilder result = new StringBuilder();
        for (String s : stack)
            result.append("/").append(s);

        return result.toString();
    }
}
