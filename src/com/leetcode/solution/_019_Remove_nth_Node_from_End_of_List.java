package com.leetcode.solution;

class _019_Remove_nth_Node_from_End_of_List {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 开辟两个指针，且第二个指针距离第一个指针为n+1，
        // 因为要删除节点，必须获得该待删除节点的前一个节点。。。。。
        ListNode node1 = head;
        ListNode node2 = head;
        node2 = move(node2, n + 1);

        // 判定一下特殊情况
        // 比如链表长度为5，删除倒数第五个，就是删除第一个。
        if (null == move(head, n)) return head.next;

        // 移动两个指针，直到后面的指针移动到了null节点，也就是链表结束位置
        while (node2 != null) {
            node1 = move(node1, 1);
            node2 = move(node2, 1);
        }

        node1.next = node1.next.next; // 删除倒数第n个节点
        return head;
    }

    // move towards the end of list
    ListNode move(ListNode node, int step) {
        for (int i = 0; i < step; i++) {
            if (node.next == null) return null;
            node = node.next;
        }
        return node;
    }
}
