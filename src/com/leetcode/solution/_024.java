class Solution {
    public ListNode swapPairsRecursive(ListNode head) {
        // 如果传入节点为空或者传入节点的 next 为空，则直接返回传入节点。
        if (head == null || head.next == null) {
            return head;
        }
        // 定义两个节点指针分别指向传入节点和传入节点的 next 节点。
        ListNode node1 = head;
        ListNode node2 = head.next;
        // 将得到的传入节点 next 指向传入节点 next 的 next 节点。
        node1.next = swapPairs(node2.next);
        // 将得到的传入节点的 next 节点的 next 指向传入节点。
        node2.next = node1;
        // 返回重新排序后的两个节点的靠前位置节点。
        return node2;
    }
    public ListNode swapPairs(ListNode head) {
        // 如果传入节点为空或者传入节点的 next 为空，则直接返回传入节点。
        if (head == null || head.next == null) {
            return head;
        }
        // 定义新节点用于指向完成所有位置交换后的链表节点。
        ListNode node = new ListNode(0);
        // 定义指针节点用于遍历两两交换相邻节点的位置。
        ListNode p = node;
        // 当不够两个节点，则停止遍历交换位置。
        while (head != null && head.next != null) {
            // 得到相邻节点的第一个节点。
            ListNode node1 = head;
            // 得到相邻节点的第二个节点。
            ListNode node2 = head.next;
            // 将指针节点的 next 指向交换后的第一个节点，使得与之前交换位置的两节点连接。
            p.next = node2;
            // 将得到的第一个节点的 next 指向第二个节点的 next 节点。
            node1.next = node2.next;
            // 将得到的第二个节点的 next 指向第一个节点。
            node2.next = node1;
            // 将指针指向交换后的第二个节点。
            p = node1;
            // 将 head 指向新的要交换位置节点对的第一个节点。
            head = node1.next;
        }
        return node.next;
    }
}

