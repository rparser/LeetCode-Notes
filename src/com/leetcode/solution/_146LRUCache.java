package com.leetcode.solution;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;

public class _146LRUCache {
    final Node head = new Node(0, 0);
    final Node tail = new Node(0, 0);
    final Map<Integer, Node> map;
    final int capacity;

    public _146LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap(capacity);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        int res = -1;
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            insertToHead(n);
            res = n.value;
        }
        return res;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            n.value = value;
            insertToHead(n);
        } else {

            if (map.size() == capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
            }

            Node n = new Node(key, value);
            insertToHead(n);
            map.put(key, n);
        }
    }

    private void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void insertToHead(Node n) {
        Node headNext = head.next;
        head.next = n;
        headNext.prev = n;
        n.prev = head;
        n.next = headNext;
    }

    class Node {
        Node prev, next;
        int key, value;

        Node(int k, int v) {
            key = k;
            value = v;
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
