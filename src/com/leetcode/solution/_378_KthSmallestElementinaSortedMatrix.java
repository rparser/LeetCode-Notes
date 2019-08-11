package com.leetcode.solution;

/**
 * 所以在本题中我们就应该使用第二种方法，先找到数组的最大值和最小值，然后以此作为二叉搜索的左右两边，求出其中间值，
 * 然后看比该值小的有多少个，是否满足条件，如果满足条件就返回，不然就将左右边界修改为mid即可。代码入下所示：
 *
 */

public class _378_KthSmallestElementinaSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0, j = matrix[0].length - 1;
            for (int[] i : matrix) {
                while (j >= 0 && i[j] > mid) j--;
                count += (j + 1);
            }
            if (count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
