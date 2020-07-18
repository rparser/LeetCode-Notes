package com.leetcode.solution;

/**
 * 合并两个已排序数组
 * 1. 必须设置不变的prehead指针，prev指针会一直后移，并把prev.next内容加入
 * <p>
 * Time complexity : O(n+m)
 * Because exactly one of l1 and l2 is incremented on each loop iteration,
 * the while loop runs for a number of iterations equal to the sum of the lengths of the two lists.
 * All other work is constant, so the overall complexity is linear.
 * <p>
 * Space complexity : O(1)
 * The iterative approach only allocates a few pointers, so it has a constant overall memory footprint.
 */

public class _021_Merge_Two_Sorted_Lists {
    //O(n + m), O(1)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 必须设置不变的head 因为prev指针在不断后移 可能随时被修改
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) { // 如果1值小，加入1
                prev.next = l1;
                l1 = l1.next;
            } else {    // 如果2值小，加入2
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        // 加入还有剩余的LinkedList
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
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
