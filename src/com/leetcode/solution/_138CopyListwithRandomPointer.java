package com.leetcode.solution;

/**
 * Time Complexity : O(N)  because we make one pass over the original linked list.
 * Space Complexity : O(1)
 * <p>
 * 第一圈复制为A->A'->B->B'->C->C'
 * 第二圈复制Random指针
 * 第三圈拆分为A->B->C, cur.next->A'->B'-C'
 */


public class _138CopyListwithRandomPointer {
    public Node copyRandomList(Node head) {

        if (head == null) return null;

        // Creating a new weaved list of original and copied nodes.
        Node cur = head;
        while (cur != null) {

            // Cloned node
            Node copyNode = new Node(cur.val, null, null);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            copyNode.next = cur.next;
            cur.next = copyNode;
            cur = cur.next.next;
        }

        cur = head;

        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (cur != null) {
            cur.next.random = (cur.random != null) ? cur.random.next : null;
            //如果当前cur.random有指向，则cur'(cur.next)的random指向cur.random的下一个（1->3则1'->3'）
            cur = cur.next.next;
        }

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        Node dummy = new Node(0, null, null);
        Node copyPrev = dummy;
        cur = head; // A->B->C, cur.next->A'->B'-C'
        while (cur != null) {
            copyPrev.next = cur.next;
            cur.next = cur.next.next;
            copyPrev = copyPrev.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
}
