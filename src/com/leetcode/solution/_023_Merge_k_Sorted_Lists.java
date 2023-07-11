package com.leetcode.solution;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K排序链表
 * <p>
 * Time complexity : O(Nlogk) where k is the number of linked lists.
 * <p>
 * The comparison cost will be reduced to O(logk) for every pop and insertion to priority queue.
 * But finding the node with the smallest value just costs O(1) time.
 * There are N nodes in the final linked list.
 * Space complexity :
 * O(n) Creating a new linked list costs O(n) space.
 * O(k) The code above present applies in-place method which cost O(1) space.
 * And the priority queue (often implemented with heaps) costs O(k) space (it's far less than N in most situations).
 * 链表pq依次offer list(Comparator.comparingInt(o -> o.val)),while非空，next=poll,cur=next,if(next非空)则offer(next)
 */

public class _023_Merge_k_Sorted_Lists {
    //方法二分治 O(KNlogk), O(logK)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        return helper(lists, 0, lists.length - 1);
    }

    //通过mid将数组一分为二，并不断缩小规模，当规模为1时返回并开始合并
    //通过合并两个链表，不断增大其规模，整体看就是不断缩小-最后不断扩大的过程
    private ListNode helper(ListNode[] lists, int begin, int end) {
        if (begin == end)
            return lists[begin];

        int mid = begin + (end - begin) / 2;

        ListNode left = helper(lists, begin, mid);
        ListNode right = helper(lists, mid + 1, end);

        return merge(left, right);
    }

    //合并两个有序链表
    private ListNode merge(ListNode l1, ListNode l2) {
        // if anyone is longer
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        }

        l2.next = merge(l1, l2.next);
        return l2;
    }

    //方法一PQ O(KNlogk), O(k)
    public ListNode mergeKListsPQ(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for (ListNode node : lists)
            if (node != null) queue.offer(node);

        while (!queue.isEmpty()) {
            curr.next = queue.poll(); //取出node
            curr = curr.next;

            if (curr.next != null)
                queue.offer(curr.next); //把剩余部分加回pq
        }
        return dummy.next;
    }
}
