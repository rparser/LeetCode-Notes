//cur 指针利用 next 不停的遍历当前层。
//        如果 cur 的孩子不为 null 就将它接到 tail 后边，然后更新tail。
//        当 cur 为 null 的时候，再利用 dummy 指针得到新的一层的开始节点。
//        dummy 指针在链表中经常用到，他只是为了处理头结点的情况，它并不属于当前链表。
/**
 * 思路1: use BFS, easy but not constant space
 * Complexity: time O(N) space O(N) - queue
 * 思路2： Iteration - use dummy node to keep record of the next level's root to refer
 * pre travel current level by referring to root in the level above
 * Complexity: time O(N) space O(1)
 */
class Solution {
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

    public Node connect2(Node root) {
        Node dummy = new Node(0);
        Node pre = dummy;//record next root
        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;
                pre = pre.next;
            }
            if (root.right != null) {
                pre.next = root.right;
                pre = pre.next;
            }
            root = root.next;//reach end, update new root & reset dummy
            if (root == null) {
                root = dummy.next;
                pre = dummy;
                dummy.next = null;
            }
        }
        return root;
    }
}