package com.leetcode.solution;

import java.util.*;

/**
 * BFS + DFS with Optimization
 * 优化的方法是在第一步BFS的过程中, 利用一个hash map记录start到每一个结点的最短距离.
 * <p>
 * 那么在第二步DFS的过程中, 我们就不需要一个一个的尝试, 不行了还得回溯. 我们可以利用那个distance的hash map,
 * 在继续往下之前, check下一个结点的最短距离是否是当前结点最短距离加一. 是的话就继续, 不是的话就根本不需要visit那个结点了.
 */

public class _126_Word_Ladder_II {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return new ArrayList<>();
        Map<String, Set<String>> graph = new HashMap<>(); //到某个单词的map
        Map<String, Integer> dist = new HashMap<>(); //到当前单词最短距离
        bfs(beginWord, endWord, dict, graph, dist);
        if (!dist.containsKey(endWord) || dist.get(endWord) == 0) return new ArrayList<>();
        List<String> list = new ArrayList<>(Arrays.asList(beginWord));
        dfs(beginWord, endWord, graph, dist, result, list);
        return result;
    }

    private void bfs(String beginWord, String endWord, Set<String> dict,
                     Map<String, Set<String>> graph, Map<String, Integer> dist) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        dist.put(beginWord, 1);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                graph.put(curr, new HashSet<>());
                if (curr.equals(endWord))
                    return;

                char[] chs = curr.toCharArray();
                for (int k = 0; k < chs.length; k++) {
                    char save = chs[k];
                    for (char c = 'a'; c <= 'z'; c++)
                        if (c != save) {
                            chs[k] = c;
                            String nextWord = new String(chs);
                            if (dict.contains(nextWord)) {
                                graph.putIfAbsent(nextWord, new HashSet<>());
                                graph.get(curr).add(nextWord); //
                                if (!dist.containsKey(nextWord)) { //能够达到的单词
                                    dist.put(nextWord, depth + 1);  //该单词深度
                                    queue.add(nextWord);
                                }
                            }
                            chs[k] = save;
                        }

                }
            }
        }
    }

    private void dfs(String beginWord, String endWord, Map<String, Set<String>> graph, Map<String, Integer> dist,
                     List<List<String>> res, List<String> list) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(list));
            return;
        }
        int len = dist.get(beginWord);
        for (String adj : graph.get(beginWord)) {
            if (dist.get(adj) == len + 1) {
                list.add(adj);
                dfs(adj, endWord, graph, dist, res, list);
                list.remove(list.size() - 1);
            }
        }
    }
}
