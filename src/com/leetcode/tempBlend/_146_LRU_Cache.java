package com.leetcode.solution;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;

/**
 * 思路： use a HashMap + doubly linked List: (head)..(pre)<—>(cur)<—>(next)..(end)
 * Always set the head for the most recent accessed node.
 * watch edge case like 1 node in list. Deleting head/tail, we need to set head/tail simultaneously.
 * Complexity: Time O(1) Space O(N)
 */

public class _146_LRUCache {
    private Node head = null;
    private Node end = null;
    HashMap<Integer, Node> map = new HashMap<>(); //<val: node in list>
    private int capacity;

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

    public _146_LRUCache(int capacity) {
        this.capacity = capacity;
    }

    private void removeNode(Node n) {
        Node preN = n.pre;
        Node nextN = n.next;
        if (n == head) head = nextN;//watch case of deleting head & end
        if (n == end) end = preN;//
        if (preN != null) preN.next = nextN;
        if (nextN != null) nextN.pre = preN;
    }

    private void setHead(Node n) {//head is most recent val
        n.next = head;
        n.pre = null;
        if (head != null) head.pre = n;
        head = n;
        if (end == null) end = head;//actually when only head in list
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
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
