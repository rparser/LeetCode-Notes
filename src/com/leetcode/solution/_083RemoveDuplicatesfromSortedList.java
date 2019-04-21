package com.leetcode.solution;

/**
 * 删除排序链表的重复值
 * <p>
 * Time complexity : O(n).
 * Because each node in the list is checked exactly once to determine if it is a duplicate or not,
 * the total run time is O(n), where n is the number of nodes in the list.
 * <p>
 * Space complexity : O(1). No additional space is used.
 */

public class _083RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode node = head;
        while (node.next != null) { // 直到计算到最后一个节点
            if (node.val == node.next.val) node.next = node.next.next; // 如果值相等则跳过下一个
            else node = node.next; // 否则不跳过
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
