package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 两个栈，一个栈存放历史页面（后退页面）和当前页面，另一个存放未来页面（前进页面）
 */
public class _1472_Design_Browser_History {
    // backStack最晚加入的backStack.peek()为当前页面
    Deque<String> backStack = new LinkedList<>();
    Deque<String> forwardStack = new LinkedList<>();

    // constructor
    public _1472_Design_Browser_History(String homepage) {
        backStack.push(homepage);
    }

    // 使用visit后不能再forward所以需要clear
    public void visit(String url) {
        backStack.push(url);
        forwardStack.clear();
    }

    public String back(int steps) {
        // 倒退到相应步数或最后一步(backStack.size()需要至少为1保存当前页面)，backStack挨个pop出放入forwardStack
        for (int i = 0; i < steps && backStack.size() > 1; i++) {
            forwardStack.push(backStack.pop());
        }
        return backStack.peek();
    }

    public String forward(int steps) {
        // 直到forward到forwardStack已空，forwardStack挨个pop出放入backStack
        for (int i = 0; i < steps && !forwardStack.isEmpty(); i++) {
            backStack.push(forwardStack.pop());
        }
        return backStack.peek();
    }
}
