package com.leetcode.solution;

//快慢指针
public class _876_Middle_of_the_Linked_List {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        // while条件决定偶数时中点靠前还是靠后
        // 如果是fast.next!=null && fast.next.next!=null
        // 则偶数时中点靠前
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
