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

public class _160_Intersection_of_Two_Linked_Lists {
    //若相交，链表A： a+c, 链表B : b+c. a+c+b+c = b+c+a+c 。在第二个c起点相遇。若不相交，a +b = b+a 。因此相遇处是NULL
    //time O(n), space O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        //保存两个head
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            //到终点的从对方的起点重新走一遍
            //所以最后两个指针走完a+c和b+c的全程但在第二次进c时相遇
            if (pA == null)
                pA = headB;
            else
                pA = pA.next;

            if (pB == null)
                pB = headA;
            else
                pB = pB.next;
        }
        return pA;
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
