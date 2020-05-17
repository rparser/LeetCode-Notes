class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        // 1.base cases
        if (head == null || head.next == null) return head;

        // 2.哑节点,快慢指针
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head;

        while (fast != null) {
            // 楼下 Fitz 大神提示的对！
            if ((fast.next != null && fast.val != = fast.next.val) || fast.next == null) {
                if (slow.next == fast)     //   3.2 & 3.3
                {
                    slow = fast;
                } else {
                    slow.next = fast.next;
                }
            }
            fast = fast.next;
        }
        return dummy.next;
    }
}