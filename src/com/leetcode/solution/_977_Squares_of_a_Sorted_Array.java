package com.leetcode.solution;

public class _977_Squares_of_a_Sorted_Array {
    //O(N), O(N)
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int[] result = new int[n];
        int i = 0, j = n - 1;
        for (int p = n - 1; p >= 0; p--)
            //比较两个绝对值 绝对值大则放在后面
            if (Math.abs(A[i]) > Math.abs(A[j])) {
                result[p] = A[i] * A[i];
                i++;
            } else {
                result[p] = A[j] * A[j];
                j--;
            }
        return result;
    }
}
