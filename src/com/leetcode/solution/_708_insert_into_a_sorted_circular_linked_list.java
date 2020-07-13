package com.leetcode.solution;

class _708_insert_into_a_sorted_circular_linked_list {
    //升序思路： 要插入的元素有以下三种状况
    //1.要插入的元素处于两个节点元素的中间
    //2. 插入的是走愈大的元素
    // 3.插入的是最小的元素
    //O(N),O(1)
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            //创建循环链表并返回
            Node node = new Node(insertVal);
            //指向自己
            node.next = node;
            return node;
        }
        //如果不为空
        Node curr = head;
        while (curr.next != head) {
            //找到了值
            if (insertVal >= curr.val && insertVal <= curr.next.val)
                break;
            //升序curr> curr.next说明curr到结尾，insert>curr说明插入最大值
            else if (curr.val > curr.next.val && insertVal >= curr.val)
                break;
                //升序curr> curr.next说明curr到结尾，insert<curr.next说明插入最小值
            else if (curr.val > curr.next.val && insertVal <= curr.next.val)  //插入最小值
                break;

            curr = curr.next;
        }
        //插入节点
        Node insertNode = new Node(insertVal);
        insertNode.next = curr.next;
        curr.next = insertNode;
        return head;
    }

    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    ;
}
