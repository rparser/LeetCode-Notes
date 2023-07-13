package com.leetcode.solution;

class _203_Remove_Linked_List_Elements {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        if (head.val == val) {
            head.next = removeElements(head.next, val);
            return head.next;
        } else {
            head.next = removeElements(head.next, val);
            return head;
        }
    }

    public ListNode removeElements2(ListNode head, int val) {
        while (head != null && head.val == val)
            // 与删除重复元素不一样的地方！！第一个元素 开头几个元素都是指定值的情况，需要先把这个情况排除
            head = head.next;
        if (head == null)
            return null;
        ListNode temp = head;
        while (temp.next != null) {
            if (temp.next.val != val)
                temp = temp.next;
            else {
                ListNode cursor = temp.next;
                while (cursor.next != null && cursor.next.val == val)
                    cursor = cursor.next;
                temp.next = cursor.next;
            }
        }
        return head;
    }
}
