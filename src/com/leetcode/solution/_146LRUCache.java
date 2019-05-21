package com.leetcode.solution;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;

/**
 * 思路： use a HashMap + doubly linked List: (head)..(pre)<—>(cur)<—>(next)..(end)
 *            Always set the head for the most recent accessed node.
 *            watch edge case like 1 node in list. Deleting head/tail, we need to set head/tail simultaneously.
 * Complexity: Time O(1) Space O(N)
 *
 */

public class _146LRUCache {
    //instance variable
    private Node head = null;
    private Node end = null;
    HashMap<Integer, Node> map = new HashMap<>(); //<val, node in list>
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

    public _146LRUCache(int capacity) {
        this.capacity = capacity;
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
            if (map.size() >= capacity) {
                map.remove(end.key);
                removeNode(end);
            }
            Node cur = new Node(key, value);
            map.put(key, cur);
            setHead(cur);
        } else {//update value
            Node cur = map.get(key);
            cur.val = value;
            removeNode(cur);
            setHead(cur);
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
        JUnitCore.main("com.leetcode.solution._146LRUCache");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(true, true);
    }
}
