package com.leetcode.solution;


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
