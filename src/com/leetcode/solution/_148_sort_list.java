package com.leetcode.solution;

//首先要先确立链表的长度，然后通过for循环对链表元素进行倍速遍历（每次将上一步的两部分合为一部分）
//定义first和second指针指向每一部分的头结点，每次事先保存每一部分的后继结点再将其断链送入Merge进行排序
// 将排序好的链表用pre指针连接起来，再将剩下的remain部分链表挂到尾部进入下一轮翻倍循环
//等到最后一轮for循环结束，如有剩余元素就挂在已排序链表尾部，如果恰好等分排好整条链表，就返回dummy.next即可。
class _148_sort_list {
    // O(N*logN), O(1), 归并排序
    public ListNode sortList(ListNode head) {
        // 归并排序
        int len = 0;
        for (ListNode i = head; i != null; i = i.next)
            len++;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 第一层循环，分块，从1个一块，2个一块，4个一块，直到n个一块，
        for (int i = 1; i < len; i = 2 * i) {
            ListNode begin = dummy;
            // 开始归并
            // j + i < n 表示只有一段就不归并了，因为已经是排好序的
            for (int j = 0; j + i < len; j = j + 2 * i) {
                // 两块，找两块的起始节点
                // 开始都指向第一块的起点
                // 然后second走n步指向第二块的起点
                ListNode first = begin.next, second = first;
                for (int k = 0; k < i; k++) second = second.next;

                // 遍历第一块和第二块进行归并
                // 第一块的数量为i
                // 第二块的数量为i也可能小于i，所以循环条件要加一个second != null
                int f = 0, s = 0;
                while (f < i && s < i && second != null) {
                    if (first.val < second.val) {
                        begin.next = first;
                        begin = begin.next;
                        first = first.next;
                        f++;
                    } else {
                        begin.next = second;
                        begin = begin.next;
                        second = second.next;
                        s++;
                    }
                }
                // 归并之后可能又多余的没有处理
                while (f < i) {
                    begin.next = first;
                    begin = begin.next;
                    first = first.next;
                    f++;

                }
                while (s < i && second != null) {
                    begin.next = second;
                    begin = begin.next;
                    // second已经更新到下一块的起点了
                    second = second.next;
                    s++;
                }

                // 更新begin
                // begin.next 指向下一块的起点
                begin.next = second;
            }
        }
        return dummy.next;
    }

    public ListNode sortList2(ListNode head) {
        // 确定链表长度
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 每次结合的元素翻倍实现由零到整的过程
        for (int m = 1; m < len; m *= 2) {
            ListNode pre = dummy;
            cur = pre.next;
            // 每次取出两部分归并排序后合并成一个部分
            while (cur != null) {
                // 定义 first 指针指向第一部分
                ListNode first = cur;
                for (int i = 0; i < m - 1 && cur != null; i++) {
                    cur = cur.next;
                }
                if (cur == null) {
                    break;
                }
                // 定义 second 指针指向第二部分
                ListNode second = cur.next;
                // 将第一部分和第二部分断开
                cur.next = null;
                cur = second;
                for (int i = 0; i < m - 1 && cur != null; i++) {
                    cur = cur.next;
                }
                ListNode remain = null;
                // 将第二部分与第三部分断开
                if (cur != null) {
                    remain = cur.next;
                    cur.next = null;
                }
                cur = remain;
                // 准备开始归并排序,并用 tmp 指向归并后的头结点
                ListNode tmp = merge(first, second);
                pre.next = tmp;
                // pre 结点遍历到归并链表的末尾
                while (pre.next != null) {
                    pre = pre.next;
                }
                // 接上剩下未排序的元素
                pre.next = remain;
            }
        }
        return dummy.next;
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                cur = cur.next;
                a = a.next;
            } else {
                cur.next = b;
                cur = cur.next;
                b = b.next;
            }
        }
        if (a != null) {
            cur.next = a;
        }
        if (b != null) {
            cur.next = b;
        }
        return pre.next;
    }
}