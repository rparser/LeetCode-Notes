package com.leetcode.solution;

/**
 * 思路： when get to the point length is same, u will get intersection or null by traverse both lists at one time
 * 1, Get the length of the two lists.
 * 2, Align them to the same start point.
 * 3, Move them together until finding the intersection point, or the end null
 * <p>
 * Complexity: time O(n), space O(1)
 * 关键字： get length of linked list,
 */

public class _160_IntersectionofTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = length(headA), lenB = length(headB);
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenA < lenB) {
            headB = headB.next;
            lenB--;
        }
        //get the point where rest len of A,B are same
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headB;
    }

    public int length(ListNode head) {
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
