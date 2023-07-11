package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class _206_Reverse_LinkedList {
    // O(N), O(1)
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;

            cur.next = pre;//反转

            pre = cur;  //更新pre
            cur = next; //更新next

        }
        return pre;
    }

    // O(N), O(N)
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // 先找到最后一个
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._206ReverseLinkedList");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(true, true);
    }
}
