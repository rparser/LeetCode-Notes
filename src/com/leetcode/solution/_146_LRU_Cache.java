package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.HashMap;

/**
 * 思路： use a HashMap + doubly linked List: (head)..(pre)<—>(cur)<—>(next)..(end)
 * Always set the head for the most recent accessed node.
 * watch edge case like 1 node in list. Deleting head/tail, we need to set head/tail simultaneously.
 * Complexity: Time O(1) Space O(N)
 */

public class _146_LRU_Cache {
    private Node head = null;
    private Node end = null;
    HashMap<Integer, Node> map = new HashMap<>(); //<val: node in list>
    private final int capacity;

    class Node {
        Node pre;
        Node next;
        int val;
        int key;//need key fields for look back to delete in Map

        public Node(int key, int value) {
            this.val = value;
            this.key = key;
        }
    }

    public _146_LRU_Cache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node cur = map.get(key);
        removeNode(cur);
        setHead(cur);
        return cur.val;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (map.size() >= capacity) { //超过容量
                map.remove(end.key); //map删除end
                removeNode(end); //node删除end
            }
            Node cur = new Node(key, value);
            map.put(key, cur); //加入map
            setHead(cur); //新key放到head
        } else { //没有超过容量
            Node cur = map.get(key);
            cur.val = value;
            removeNode(cur); //删除原有node
            setHead(cur); //新key放到head
        }
    }

    // 需要做：取出前后节点，看是否是head或end然后交叉指向
    private void removeNode(Node node) {
        // 要取出前后节点
        Node preN = node.pre;
        Node nextN = node.next;
        // 如果删除的是head，新head要指向下一个节点
        if (node == head)
            head = nextN;
        // 如果删除的是end, 新end要指向前一个节点
        if (node == end)
            end = preN;
        // 如果preN非空，证明删除点非head,前一个节点需要指向后一个
        if (preN != null)
            preN.next = nextN;
        // 如果nextN非空，证明删除点非end,后一个节点需要指向前一个
        if (nextN != null)
            nextN.pre = preN;
    }

    //需要做：新node指到当前head,head.pre指回新node，head更新到新node
    private void setHead(Node node) {
        // 此时node在head前指向head
        node.next = head;
        node.pre = null;
        // 如果head非null（证明不是第一个加进去的）
        if (head != null)
            // head.pre需要指回新node
            head.pre = node;
        // 然后把head pointer指到新node
        head = node;
        // 如果end为空证明此时是第一个加入的节点，则end和head需要指向同一个
        if (end == null)
            end = head;
    }

// LinkedHashMap方法
//    public int getLhm(int key) {
//        if (linkedHashMap.containsKey(key)) {
//            return linkedHashMap.get(key);
//        } else
//            return -1;
//    }
//
//    public void setLhm(int key, int value) {
//        linkedHashMap.put(key, value);
//    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._146_LRUCache");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(true, true);
    }
}
