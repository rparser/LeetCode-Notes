package com.leetcode.solution;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class _141_LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode fast = head;
        ListNode slow = head;
        do {
            if (fast.next == null || fast.next.next == null || slow.next == null)
                return false;
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);

        return true;
    }
}
