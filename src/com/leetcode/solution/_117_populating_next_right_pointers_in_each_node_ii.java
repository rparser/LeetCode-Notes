package com.leetcode.solution;

/**
 * 思路1: use BFS, easy but not constant space
 * Complexity: time O(N) space O(N) - queue
 * 思路2： Iteration - use dummy node to keep record of the next level's root to refer
 * pre travel current level by referring to root in the level above
 * Complexity: time O(N) space O(1)
 */
class _117_populating_next_right_pointers_in_each_node_ii {
    // O(N), O(1)
    public Node connect2(Node root) {
        Node cur = root;
        while (cur != null) {
            //每层新建一个dummy
            Node dummy = new Node();
            Node tail = dummy;
            //遍历 cur 的当前层
            while (cur != null) {
                if (cur.left != null) {
                    tail.next = cur.left;
                    tail = cur.left;
                }

                if (cur.right != null) {
                    tail.next = cur.right;
                    tail = cur.right;
                }
                // System.out.println(cur.val+"   "+tail.val);
                // 会打印1,3 -> 2,5-> 3,7-> 4,0->5,0->7->0
                cur = cur.next;
            }
            //这里时cur一定是空值
            //更新 cur 到下一层
            cur = dummy.next;
        }
        return root;
    }

    // O(N), O(N)
    Node connect(Node root) {
        Node cur = root;
        while (cur != null) {
            Node dummy = new Node();
            Node tail = dummy;
            //遍历 cur 的当前层
            while (cur != null) {
                if (cur.left != null) {
                    tail.next = cur.left;
                    tail = tail.next;
                }
                if (cur.right != null) {
                    tail.next = cur.right;
                    tail = tail.next;
                }
                cur = cur.next;
            }
            //更新 cur 到下一层
            cur = dummy.next;
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}