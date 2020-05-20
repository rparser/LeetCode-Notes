class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode meet = null;
        while (fast != null) {
            if (fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            //到达相遇点
            if (fast == slow) {
                meet = fast;
                while (head != meet) {
                    head = head.next;
                    meet = meet.next;
                }
                return head;
            }
        }
        return null;
    }
}