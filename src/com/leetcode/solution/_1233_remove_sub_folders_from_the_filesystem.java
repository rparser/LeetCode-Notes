package com.leetcode.solution;

import java.util.*;

public class _1233_remove_sub_folders_from_the_filesystem {
    // Time: O(n * m * log(n)), space: O(1) m是平均长度
    public List<String> removeSubfolders(String[] folder) {
        LinkedList<String> ans = new LinkedList<>();
        // 所有非子文件夹都会在排序的第一个出现
        Arrays.sort(folder);
        for (String f : folder)
            //  need '/' to ensure a parent.
            if (ans.isEmpty() || !f.startsWith(ans.peekLast() + '/'))
                ans.add(f);

        return ans;
    }

    // O(n * (logn + m ^ 2)), space: (n * m)
    public List<String> removeSubfoldersAnotherSort(String[] folder) {
        Arrays.sort(folder, Comparator.comparing(String::length));
        Set<String> seen = new HashSet<>();
        outer:
        for (String f : folder) {
            for (int i = 2; i < f.length(); ++i)
                if (f.charAt(i) == '/' && seen.contains(f.substring(0, i)))
                    continue outer;

            seen.add(f);
        }
        return new ArrayList<>(seen);
    }

    // Use index to save each folder index in a trie node;
// when search the trie, if we find a folder (index >= 0) and the next char is /,
// then we get all parent folders on the current trie branch.
// Time & space: O(n * m),
    class Trie {
        Trie[] sub = new Trie[27];
        int index = -1;
    }

    public List<String> removeSubfoldersTrie(String[] folder) {
        Trie root = new Trie();
        for (int i = 0; i < folder.length; ++i) {
            Trie t = root;
            for (char c : folder[i].toCharArray()) {
                int idx = c == '/' ? 26 : c - 'a'; // correspond '/' to index 26.
                if (t.sub[idx] == null)
                    t.sub[idx] = new Trie();
                t = t.sub[idx];
            }
            t.index = i;
        }
        return bfs(root, folder);
    }

    private List<String> bfs(Trie t, String[] folder) {
        List<String> ans = new ArrayList<>();
        Queue<Trie> q = new LinkedList<>();
        q.offer(t);
        while (!q.isEmpty()) { // BFS search.
            t = q.poll();
            if (t.index >= 0) { // found a parent folder, but there might be more.
                ans.add(folder[t.index]);
            }
            for (int i = 0; i < 27; ++i)
                if (t.sub[i] != null && !(i == 26 && t.index >= 0)) // not yet found all parent folders in current trie branch.
                    q.offer(t.sub[i]);
        }
        return ans;
    }
}
