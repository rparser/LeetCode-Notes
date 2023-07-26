package com.leetcode.doordash;

import java.util.*;

/**
 * TC: O(m + n)
 * SC: O(m + n)
 * m: Total nodes in Tree-1
 * n: Total nodes in Tree-2
 * 注意follow up
 */

public class _Trie_menu_变了多少Nodes {
    public static class Node {
        String key;
        int value;
        boolean isActive;
        List<Node> children;

        public Node(String key, int value, boolean isActive) {
            this.key = key;
            this.value = value;
            this.isActive = isActive;
            this.children = new ArrayList<>();
        }

        public boolean equals(Node node) {
            if(node == null) {
                return false;
            }

            return Objects.equals(this.key, node.key)
                    && this.value == node.value
                    && this.isActive == node.isActive;
        }

        public String toString() {
            return this.key;
        }
    }

    public static int getModifiedItems(Node oldMenu, Node newMenu) {
        if(oldMenu == null && newMenu == null) {
            return 0;
        }

        int count = 0;

        if(oldMenu == null || newMenu == null || !oldMenu.equals(newMenu)) {
            System.out.println(oldMenu + " " + newMenu);
            count++;
        }
        // 用map保存此node的所有Children
        Map<String, Node> oldChildren = getChildNodes(oldMenu);
        Map<String, Node> newChildren = getChildNodes(newMenu);

        // 遍历旧的子节点 - 旧的和新的比较
        for(String key : oldChildren.keySet()) {
            count += getModifiedItems(oldChildren.get(key), newChildren.getOrDefault(key, null));
        }

        // 遍历新的子节点 - 这次必须是新的新增的才能增加(旧的里面没有)
        for(String key : newChildren.keySet()) {
            if(!oldChildren.containsKey(key)) {
                count += getModifiedItems(null, newChildren.get(key));
            }
        }

        return count;
    }

    private static Map<String, Node> getChildNodes(Node menu) {
        Map<String, Node> map = new HashMap<>();
        if(menu == null) {
            return map;
        }

        for(Node node : menu.children) {
            map.put(node.key, node);
        }

        return map;
    }

    public static void main(String[] args) {

/*
         Existing tree
            a(1, T)
          /         \
        b(2, T)   c(3, T)
      /       \
  d(4, T) e(5, T)

                New tree
                a(1, T)
             /          \
       b(2, T)         c(3, T)
      /                   \
 d(4, T)                   e(5, T)

 */

        Node a = new Node("a", 1, true);
        Node b = new Node("b", 2, true);
        Node c = new Node("c", 3, true);
        Node d = new Node("d", 4, true);
        Node e = new Node("e", 5, true);
        Node g = new Node("g", 7, true);

        a.children.add(b);
        a.children.add(c);

        b.children.add(d);
        b.children.add(e);

        //c.children.add(g);

        Node a1 = new Node("a", 1, true);
        Node b1 = new Node("b", 2, true);
        Node c1 = new Node("c", 3, true);
        Node d1 = new Node("d", 4, true);
        Node e1 = new Node("e", 5, true);
        Node f1 = new Node("f", 6, true);
        Node g1 = new Node("g", 7, false);

        a1.children.add(b1);
        a1.children.add(c1);

        b1.children.add(d1);
        //b1.children.add(e1);
        //b1.children.add(f1);

        c1.children.add(e1);

        int count = getModifiedItems(a, a1);
        System.out.println("Changed Items are: " + count);
    }
}
