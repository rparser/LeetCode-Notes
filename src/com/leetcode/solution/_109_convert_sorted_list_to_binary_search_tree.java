package com.leetcode.solution;

//利用快慢指针。
class _109_convert_sorted_list_to_binary_search_tree {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        //当链表只剩一个元素时，直接构造节点。否则会造成栈溢出。
        if (head.next == null) return new TreeNode(head.val);

        ListNode slow = head, fast = head, prev = null;
        //利用快慢双指针寻找中间节点
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //将中间节点断开
        if (prev != null)
            prev.next = null;
        //构造根节点
        TreeNode root = new TreeNode(slow.val);
        //构造左子树
        root.left = sortedListToBST(head);
        //构造右子树，从中间节点的下个节点开始
        root.right = sortedListToBST(slow.next);
        return root;
    }
}
