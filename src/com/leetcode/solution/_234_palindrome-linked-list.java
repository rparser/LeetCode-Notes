package com.leetcode.solution;

/**
 * 双指针一快一慢，快的到尾部，慢的到中间
 * 判断是奇是偶
 * 慢的翻转后半部分，快的从head开始
 * 不一致则返回false，否则返回true
 */
public class _234PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) slow = slow.next;
        // fast每次两步，所以为null证明是奇数个，此时slow要到下一个
        slow = reverseList(slow); // 从翻转后的下半部分开始
        fast = head; //从头开始
        while (slow != null) {
            if (slow.val != fast.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) { // _206原题翻转LinkedList
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
}
