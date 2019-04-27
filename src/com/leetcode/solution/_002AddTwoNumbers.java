package com.leetcode.solution;

/**
 * 链表加法
 * <p>
 * Time complexity : O(max(m,n)).
 * Assume that m and n represents the length of l1 and l2 respectively, the algorithm above iterates at most max(m,n) times.
 * Space complexity : O(max(m,n)). The length of the new list is at most max(m,n)+1.
 */


public class _002AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode p = l1, q = l2, curr = result;
        int carry = 0; // 进位
        while (p != null || q != null) { // 计算到两个都为null为止
            int x = (p != null) ? p.val : 0; // 其中有一个为null则设置成0
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10; // 进位
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry == 1) { // 如果最后还有一位
            curr.next = new ListNode(carry);
        }
        return result.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
