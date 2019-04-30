package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

public class _206ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = curr.next; // 下一个node存储在临时位置
        while (curr != null) {
            curr.next = prev; // 当前next指向前一个node
            prev = curr;  // 当前curr已经计算完毕 赋值前一个prev
            curr = next;  // 保存的temp赋值给当前curr计算下一个
            if (curr != null) next = curr.next;
        }
        return prev;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._206ReverseLinkedList");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(true, true);
    }
}
