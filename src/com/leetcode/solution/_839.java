//时间复杂度是O(N^2 * L)
class Solution {
    public int numSimilarGroups(String[] A) {
        int[] uset = new int[A.length];
        for (int i = 0; i < A.length; i++) uset[i] = i;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (isSimilar(A[i], A[j])) union(uset, i, j);
            }
        }
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (uset[i] == i) count++;
        }
        return count;
    }

    private int find(int[] uset, int i) {
        if (uset[i] == i) return i;
        uset[i] = find(uset, uset[i]);
        return uset[i];
    }

    private void union(int[] uset, int i, int j) {
        int rooti = find(uset, i);
        int rootj = find(uset, j);
        uset[rooti] = rootj;
    }

    private boolean isSimilar(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (++count > 2) return false;
            }
        }
        return true;
    }
}
