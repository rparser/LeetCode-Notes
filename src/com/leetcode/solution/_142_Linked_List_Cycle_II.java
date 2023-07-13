package com.leetcode.solution;

/**
 * 找到入环的节点index
 * Move pointer2 ahead by ‘K’ nodes.
 * Now, keep incrementing pointer1 and pointer2 until they both meet.
 * As pointer2 is ‘K’ nodes ahead of pointer1, which means,
 * pointer2 must have completed one loop in the cycle when both pointers meet. Their meeting point will be the start of the cycle.
 */
class _142_Linked_List_Cycle_II {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode meet = null;
        while (fast != null) {
            if (fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            //到达相遇点
            if (fast == slow) {
                meet = fast;
                while (head != meet) {
                    head = head.next;
                    meet = meet.next;
                }
                return head;
            }
        }
        return null;
    }

    //计算长度
    public static int findCycleLength(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) // found the cycle
                return calculateLength(slow);
        }
        return 0;
    }

    private static int calculateLength(ListNode slow) {
        ListNode current = slow;
        int cycleLength = 0;
        do {
            current = current.next;
            cycleLength++;
        } while (current != slow);
        return cycleLength;
    }
}
