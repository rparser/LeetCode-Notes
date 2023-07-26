package com.leetcode.doordash;

import java.util.HashMap;
import java.util.Map;

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 * <p>
 * 基础的Trie操作
 */

public class _1166_Trie设计文件系统 {
    private final TrieNode trie = new TrieNode(-1);

    public boolean createPath(String path, int value) {
        return trie.insert(path, value);
    }

    public int get(String path) {
        return trie.search(path);
    }

    public boolean set(String path, int value) {
        return trie.set(path, value);
    }

    public boolean delete(String path) {
        return trie.delete(path);
    }
}

class TrieNode {
    Map<String, TrieNode> children = new HashMap<>();
    int value;

    TrieNode(int value) {
        this.value = value;
    }

    // 不一定需要
    TrieNode(int value, Map<String, TrieNode> children) {
        this.value = value;
        this.children = children;
    }

    boolean insert(String path, int value) {
        TrieNode node = this;
        String[] arr = path.split("/");
        for (int i = 1; i < arr.length - 1; i++) {
            // 如果不能继续向下直接返回-1
            if (!node.children.containsKey(arr[i])) {
                // throw new Exception("unable to locate the path")
                return false;
            }
            node = node.children.get(arr[i]);
        }
        // 跳出for loop已经到最后一层, 需要加入时如果已经存在
        if (node.children.containsKey(arr[arr.length - 1])) {
            // throw new Exception("path already exist")
            return false;
        }
        // 通过所有false后才是插入成功
        node.children.put(arr[arr.length - 1], new TrieNode(value));
        return true;
    }

    int search(String path) {
        TrieNode node = this;
        String[] arr = path.split("/");
        for (int i = 1; i < arr.length; i++) {
            // 如果不能继续向下直接返回-1
            if (!node.children.containsKey(arr[i])) {
                return -1;
            }
            // 到child继续recursion
            node = node.children.get(arr[i]);
        }
        // 退出for loop返回最后一个值
        return node.value;
    }

    boolean set(String path, int value) {
        TrieNode node = this;
        String[] arr = path.split("/");
        for (int i = 1; i < arr.length - 1; i++) {
            // 如果不能继续向下直接返回-1
            if (!node.children.containsKey(arr[i])) {
                // throw new Exception("unable to locate the path")
                return false;
            }
            node = node.children.get(arr[i]);
        }
        // 跳出for loop已经到最后一层, 需要加入时如果不存在当前path
        if (!node.children.containsKey(arr[arr.length - 1])) {
            // throw new Exception("path is not exist to set")
            return false;
        }

        TrieNode temp = node.children.get(arr[arr.length - 1]);
        // 有可能只能set leaf node，那么：
        if (temp.children != null) {
            // throw new Exception("unable to set because children is not null")
            return false;
        }
        // 通过所有false后才是修改成功
        node.children.put(arr[arr.length - 1], new TrieNode(value));
        return true;
    }

    boolean delete(String path) {
        TrieNode node = this;
        String[] arr = path.split("/");
        for (int i = 1; i < arr.length - 1; i++) {
            // 如果不能继续向下直接返回-1
            if (!node.children.containsKey(arr[i])) {
                // throw new Exception("unable to locate the path")
                return false;
            }
            node = node.children.get(arr[i]);
        }
        // 跳出for loop已经到最后一层, 需要删除时时如果不存在当前path
        if (!node.children.containsKey(arr[arr.length - 1])) {
            // throw new Exception("path is not existed to delete")
            return false;
        }

        TrieNode temp = node.children.get(arr[arr.length - 1]);
        // 只能删除没有children的path
        if (temp.children != null) {
            // throw new Exception("unable to delete because children is existed")
            return false;
        }
        // 删除这个key
        node.children.remove(arr[arr.length - 1]);
        return true;
    }
}

//    public _1166_Design_File_System() {
//        root = new TrieNode();
//    }
//
//    private Trie trie = new Trie(-1);
//
//    public FileSystem() {
//    }
//
//    public boolean createPath(String path, int value) {
//        return trie.insert(path, value);
//    }
//
//    public int get(String path) {
//        return trie.search(path);
//    }
//}

//class TrieNode {
//    Map<String, TrieNode> children;
//    Integer val;
//
//    public TrieNode() {
//        children = new HashMap<>();
//    }
//
//    public boolean createPath(String path, int value) {
//        TrieNode cur = root;
//        String[] arr = path.split("/");
//        for (int i = 1; i < arr.length; i++) {
//            // 当没有找到时，代表有可能能加入
//            if (!cur.children.containsKey(arr[i])) {
//                // 如果是最后一位才可以加入 (如果可以直接建立多层则不需要这个if)
//                if (i == arr.length - 1) {
//                    cur.children.put(arr[i], new TrieNode());
//                    // 否则返回false因为已经有了
//                } else {
////                    throw new Exception();
//                    return false;
//                }
//            }
//
//            cur = cur.children.get(arr[i]);
//        }
//        // 如果这个node val存在,返回false建立失败
//        if (cur.val != null) {
//            return false;
//        }
//
//        cur.val = value;
//        return true;
//    }
//
//    public int get(String path) {
//        TrieNode cur = root;
//        String[] arr = path.split("/");
//        for (int i = 1; i < arr.length; i++) {
//            // 如果找不到，直接返回-1
//            if (!cur.children.containsKey(arr[i])) {
//                return -1;
//            }
//            // 找到了到下一个node
//            cur = cur.children.get(arr[i]);
//        }
//
//        return cur.val == null ? -1 : cur.val;
//    }
//
//    // 必须找到才能set
//    public boolean set(String path, int value) {
//        TrieNode cur = root;
//        String[] arr = path.split("/");
//        for (int i = 1; i < arr.length; i++) {
//            // 如果找不到，直接返回-1
//            if (!cur.children.containsKey(arr[i])) {
//                if (i == arr.length - 1) {
//                    cur.val = value;
//                    // 否则返回false因为已经有了
//                } else {
////                    throw new Exception();
//                    return false;
//                }
//            }
//            // 找到了到下一个node
//            cur = cur.children.get(arr[i]);
//        }
//        return true;
//    }
//
//    public boolean delete(String path, int value) throws Exception {
//        TrieNode cur = root;
//        String[] arr = path.split("/");
//        for (int i = 1; i < arr.length; i++) {
//            // 如果找不到，直接返回-1
//            if (!cur.children.containsKey(arr[i])) {
//                if (i == arr.length - 1) {
//                    cur.val = value;
//                    // 否则返回false因为已经有了
//                } else {
////                    throw new Exception("Can't delete because is not empty");
//                    return false;
//                }
//            }
//            // 找到了到下一个node
//            cur = cur.children.get(arr[i]);
//        }
//        return true;
//    }
