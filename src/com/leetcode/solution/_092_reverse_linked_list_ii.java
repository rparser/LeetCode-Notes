package com.leetcode.solution;

class _092_reverse_linked_list_ii {
    //O(N) ~ O(q), O(1)
    public static ListNode reverse(ListNode head, int p, int q) {
        if (p == q)
            return head;
        // current是第p个node, 一共移动p-1格
        ListNode current = head, previous = null;
        for (int i = 1; current != null && i < p; i++) {
            previous = current;
            current = current.next;
        }
        // 比如1,2,3,4,5,6,7反转3 -5
        // 此时previous是2要保存起来
        ListNode lastNodeOfFirstPart = previous; // points to the node at index 'p-1'
        // lastNodeOfSubList 是3
        ListNode lastNodeOfSubList = current;
        ListNode next = null; // will be used to temporarily store the next node
        //一共run q - p + 1次
        for (int i = 0; current != null && i < q - p + 1; i++) {
            next = current.next;
            // current.next指向前一个值
            current.next = previous;
            //prev和current分别前进一位
            previous = current;
            current = next;
        }

        //此时previous是5，current是6
        if (lastNodeOfFirstPart != null)
            //此时2.next要指向5
            lastNodeOfFirstPart.next = previous; // 'previous' is now the first node of the sub-list
        else // this means p == 1 i.e., we are changing the first node (head) of the LinkedList
            head = previous;

        //3.next指向6
        lastNodeOfSubList.next = current;

        return head;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode node = result;
        //找到需要反转的那一段的上一个节点。
        for (int i = 1; i < m; i++) {
            node = node.next;
        }
        //node.next就是需要反转的这段的起点。
        ListNode nextHead = node.next;
        ListNode next = null;
        ListNode pre = null;
        //反转m到n这一段
        for (int i = m; i <= n; i++) {
            next = nextHead.next;
            nextHead.next = pre;
            pre = nextHead;
            nextHead = next;
        }
        //将反转的起点的next指向next。
        node.next.next = next;
        //需要反转的那一段的上一个节点的next节点指向反转后链表的头结点
        node.next = pre;
        return result.next;
    }
}