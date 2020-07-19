package com.leetcode.solution;

import java.util.*;

//先union email to have same parent
//然后写一个map key存parent, value用treemap存这些相同parent的email
//        最后再遍历这个map 将name插在前面
//O(AlogA), O(A)
class _721_accounts_merge {
    class Node {
        Node parent;
        String name;
        String email;
    }
    // <email, node> 一共多少个不同的email
    Map<String, Node> map = new HashMap<>();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        for (List<String> account : accounts) {
            String name = account.get(0);
            String s = account.get(1);
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
                // union到第一个email(设置为第一个email的parent)
                union(map.get(s), map.get(curEmail));
            }
        }
        // 合并到同一个人
        Map<String, Set<String>> res = new HashMap<>();
        for (Map.Entry<String, Node> e : map.entrySet()) {
            // 找到最高级别的parent
            Node key = find(map.get(e.getKey()));
            //保持顺序，所以第一个还是姓名
            res.putIfAbsent(key.email, new TreeSet<>());
            res.get(key.email).add(e.getKey());
        }
        // 生成结果
        List<List<String>> result = new ArrayList<>();
        for (Set<String> set : res.values()) {
            List<String> temp = new ArrayList<>(set);
            //第一个加为姓名
            temp.add(0, map.get(temp.get(0)).name);
            result.add(temp);
        }
        return result;
    }

    public void union(Node x, Node y) {
        Node x1 = find(x);
        Node y1 = find(y);
        y1.parent = x1;
    }

    public Node find(Node x) {
        if (x != x.parent)
            x.parent = find(x.parent);

        return x.parent;
    }
}
