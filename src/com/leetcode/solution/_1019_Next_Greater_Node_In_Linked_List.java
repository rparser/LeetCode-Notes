package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

public class _1019_Next_Greater_Node_In_Linked_List {

//    给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
//
//    每个节点都可能有下一个更大值（next larger value）：对于 node_i，
//    如果其 next_larger(node_i) 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，
//            而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。
//
//    返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。
//
//    注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。


    // monotonic stack
    public int[] nextLargerNodes(ListNode head) {
        int[] arr = new int[10000];
        int[] valueArr = new int[10000];
        Deque<Integer> stack = new LinkedList<>();
        int length = 0;
        int value;
        while (head != null) {
            value = head.val;
            valueArr[length] = value;
            while (!stack.isEmpty() && value > valueArr[stack.peek()]) {
                arr[stack.pop()] = value;
            }
            stack.add(length);
            length++;
            head = head.next;
        }
        int[] result = new int[length];
        System.arraycopy(arr, 0, result, 0, length);
        return result;
    }
}
