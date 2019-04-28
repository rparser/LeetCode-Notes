package com.leetcode.solution;

/**
 * 翻转链表K组
 * 参考206翻转链表
 */

public class _025ReverseNodesinkGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        while (count < k && cur != null) {
            cur = cur.next;
            count++;
        }
        //分段递归
        if (count == k) {
            cur = reverseKGroup(cur, k); //cur存储反转k各节点之后的链表
            while (count > 0) { //反转本段链表，相当于以head为指针再一次遍历前k个节点，把从head开始的每一个节点放到cur前，然后head向后移动，cur向前移动，直到k次
                ListNode nextHead = head.next;
                head.next = cur;
                cur = head;
                head = nextHead;
                count--;
            }
            head = cur; //把head重新放回到表头用以返回结果
        }
        return head;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next; // 下一个node存储在临时位置
            curr.next = prev; // 当前next指向前一个node
            prev = curr;  // 当前curr已经计算完毕 赋值前一个prev
            curr = temp;  // 保存的temp赋值给当前curr计算下一个
        }
        return prev;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
