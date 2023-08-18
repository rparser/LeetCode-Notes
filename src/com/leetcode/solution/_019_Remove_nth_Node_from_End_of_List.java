package com.leetcode.solution;

class _019_Remove_nth_Node_from_End_of_List {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 开辟两个指针，且第二个指针距离第一个指针为n+1，
        // 因为要删除节点，必须获得该待删除节点的前一个节点。。。。。
        ListNode slow = head;
        ListNode fast = head;
        fast = move(fast, n + 1);

        // 判定一下特殊情况
        // 比如链表长度为5，删除倒数第五个，就是删除第一个。
        if (null == move(head, n)) {
            return head.next;
        }

        // 移动两个指针，直到后面的指针移动到了null节点，也就是链表结束位置
        while (fast != null) {
            slow = move(slow, 1);
            fast = move(fast, 1);
        }

        slow.next = slow.next.next; // 删除倒数第n个节点
        return head;
    }

    // move towards the end of list
    ListNode move(ListNode node, int step) {
        for (int i = 0; i < step; i++) {
            if (node.next == null) {
                return null;
            }
            node = node.next;
        }
        return node;
    }
}
