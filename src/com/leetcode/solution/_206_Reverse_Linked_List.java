package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/**
 * 设置pre=null和cur=head，pre作为head
 * while(cur不为空)：分四步
 * 1,temp保存当前的next -> 临时保存
 * 2,当前的next变为pre -> next(比如是1)变成pre指回去
 * 3,pre变成cur -> 此时pre已无用变成cur(pre.next)
 * 4,更新cur到temp -> 从1到2或是说变成cur.next
 */

public class _206_Reverse_Linked_List {
    // O(N), O(1)
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tempNext = cur.next;
            cur.next = pre;//反转
            pre = cur;  //更新pre
            cur = tempNext; //更新next

        }
        return pre;
    }

    // O(N), O(N)
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // 先找到最后一个
        ListNode node = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._206ReverseLinkedList");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(true, true);
    }
}
