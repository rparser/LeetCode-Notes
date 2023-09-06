package com.leetcode.solution;

public class _430_Flatten_a_Multilevel_Doubly_Linked_List {
    public Node flatten(Node head) {
        // 遍历每一个节点，看它是否有子节点，有子节点的话，把它的子节点那一坨放在它和next之间
        dfs(head);
        return head;
    }

    private void dfs(Node node) {
        // 退出条件
        if (node == null) {
            return;
        }

        // 子节点不为空，需要处理
        if (node.child != null) {
            // 记录下来下一个节点
            Node next = node.next;

            // 先把子链表扁平化
            dfs(node.child);

            // 修改当前节点的下一个节点为子链表的头
            // 同时，子链表头的上一个节点为当前节点
            node.next = node.child;
            node.child.prev = node;

            // 寻找扁平化之后的子节点的最后一个节点（即子链表的尾）
            Node tail = node.child;
            while (tail.next != null) {
                tail = tail.next;
            }

            // 把子链表的尾与原来当前节点的下一个节点连起来
            // 注意空指针的处理
            if (next != null) {
                next.prev = tail;
            }
            tail.next = next;

            // 子链表置空
            node.child = null;

            // 从原来的next节点继续
            // 测试用例有漏洞，这行不加也不报错
            // 因为局限于序列化方式，每一层最多有一个节点有子节点
            // 所以不加下面这行也没报错。。。
            dfs(next);
        } else {
            // 子节点为空，不需要处理
            dfs(node.next);
        }
    }
}
