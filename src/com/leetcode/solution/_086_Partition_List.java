package com.leetcode.solution;

class _086_Partition_List {
    public ListNode partition(ListNode head, int x) {
        ListNode minLink = new ListNode(0);//记录小值链表的头
        ListNode minP = minLink;//对小表操作用的指针

        ListNode maxnLink = new ListNode(0);//记录大值链表的头
        ListNode maxP = maxnLink;//同理

        while (head != null) {
            if (head.val < x) {//找到小的值
                minP.next = head;//放入minLink中，操作指针后移一位
                minP = head;

            } else {
                maxP.next = head;//放入maxLink中，操作指针后移一位
                maxP = head;
            }
            head = head.next;
        }
        //遍历完成后记得后一段链表的最后节点指向null;
        maxP.next = null;
        //两段拼接
        minP.next = maxnLink.next;

        return minLink.next;
    }
}
