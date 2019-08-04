package com.leetcode.solution;

import java.util.*;

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

public class _023_MergekSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for (ListNode node : lists)
            if (node != null) queue.offer(node);

        while (!queue.isEmpty()) {
            curr.next = queue.poll(); //取出node
            curr = curr.next;

            if (curr.next != null) queue.offer(curr.next); //把剩余部分加回pq
        }
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}