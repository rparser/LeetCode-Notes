package com.leetcode.solution;

//首先将原节点的子节点分离出来作为第二个头结点
//        将奇链表的下一个节点指定
//        将偶链表的下一个节点指定
//        重复1,2操作直到出现空的3种情况
//        最后将偶链表拼到奇链表的后面
class _328_odd_even_linked_list {
    //从0开始index, O(N), O(1)
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        // 保存两个tmp
        ListNode evenTmp = head;
        ListNode oddTemp = head.next;
        //保存odd指针最后连上
        ListNode odd = head.next;
        // 每一个指针都向后放一位,evenTmp不会是null是因为evenTmp在oddTmp前判断
        // 只要oddTmp非null则evenTmp必然非null
        while (oddTemp != null && evenTmp.next != null && oddTemp.next != null) {
            evenTmp.next = evenTmp.next.next;
            oddTemp.next = oddTemp.next.next;
            evenTmp = evenTmp.next;
            oddTemp = oddTemp.next;
        }
        //把两端连上
        evenTmp.next = odd;
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
