package com.leetcode.solution;

import java.util.*;

//先union email to have same parent
//然后写一个map key存parent, value用treemap存这些相同parent的email
//        最后再遍历这个map 将name插在前面
//O(AlogA), O(A)
class _721_Accounts_Merge {
    class Node {
        Node parent;
        String name;
        String email;
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // <email, node> 一共多少个不同的email
        Map<String, Node> map = new HashMap<>();
        // 把每个账号放到map里
        for (List<String> account : accounts) { // account第一个是name，后面都是email
            String name = account.get(0);
            String parentEmail = account.get(1);
            for (int i = 1; i < account.size(); i++) {
                String curEmail = account.get(i);
                // 如果没有出现过这个email
                if (!map.containsKey(curEmail)) {
                    map.put(curEmail, new Node());
                    // parent先设成自己
                    map.get(curEmail).parent = map.get(curEmail);
                    map.get(curEmail).name = name;
                    map.get(curEmail).email = curEmail;
                }
                // union到第一个email (第一个邮箱是所有其他邮箱的parent)
                union(map.get(parentEmail), map.get(curEmail));
            }
        }
        // 合并不同账号到同一个人 - 已经合并好的map(可以制作成resultList了)
        Map<String, Set<String>> unionedMap = new HashMap<>();
        for (Map.Entry<String, Node> e : map.entrySet()) {
            // 找到最高级别的parent
            Node parentNode = find(map.get(e.getKey()));
            // TreeSet保持邮箱字母顺序
            unionedMap.putIfAbsent(parentNode.email, new TreeSet<>());
            unionedMap.get(parentNode.email).add(e.getKey());
        }
        // 生成结果
        List<List<String>> result = new ArrayList<>();
        for (Set<String> set : unionedMap.values()) {
            List<String> temp = new ArrayList<>(set);
            // 添加第一个加为姓名
            temp.add(0, map.get(temp.get(0)).name);
            result.add(temp);
        }
        return result;
    }

    // y和x合并，且x成为y的parent
    public void union(Node x, Node y) {
        Node x1 = find(x);
        Node y1 = find(y);
        y1.parent = x1;
    }

    // 一直找到根节点
    public Node find(Node x) {
        if (x != x.parent) { // 如果不是自己的parent
            x.parent = find(x.parent); // 递归
        }
        return x.parent;
    }
}
