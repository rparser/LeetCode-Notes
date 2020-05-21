class Solution {
    public void deleteNode(ListNode node) {
        //由于没有给定该链表的头结点，因此只能从node向后看，题目中已经说定不包括末尾节点
        //删除node即通过将后面的值赋给node，然后更改node的指针指向下下一个结点即可
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
