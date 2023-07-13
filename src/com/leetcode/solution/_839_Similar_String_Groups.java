package com.leetcode.solution;

//O(N^2 * L), O(N L^3)
//互为anagram,并查集
class _839_Similar_String_Groups {
    public int numSimilarGroups(String[] A) {
        int[] roots = new int[A.length];

        for (int i = 0; i < A.length; i++)
            roots[i] = i;

        for (int u = 0; u < A.length; u++)
            for (int v = 0; v < A.length; v++)
                if (isSimilar(A[u], A[v]))
                    union(roots, u, v);


        int count = 0;
        for (int i = 0; i < A.length; i++)
            if (roots[i] == i)
                count++;

        return count;
    }

    private int find(int[] roots, int u) {
        if (roots[u] == u)
            return u;

        roots[u] = find(roots, roots[u]);
        return roots[u];
    }

    private void union(int[] roots, int u, int v) {
        int rootU = find(roots, u);
        int rootV = find(roots, v);
        roots[rootU] = rootV;
    }

    // 判断是否只有一个字母不同
    private boolean isSimilar(String s1, String s2) {
        int count = 0;
        // counted只能使用一次，第二次就会返回true
        for (int i = 0; i < s1.length(); i++)
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
                if (count > 2)
                    return false;
            }
        return true;
    }
}
