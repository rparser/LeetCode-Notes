package com.leetcode.solution;

/**
 * 所以在本题中我们就应该使用第二种方法，先找到数组的最大值和最小值，然后以此作为二叉搜索的左右两边，求出其中间值，
 * 然后看比该值小的有多少个，是否满足条件，如果满足条件就返回，不然就将左右边界修改为mid即可。
 * 这个方法只要求行有序，和列有木有序并没有关系。 （或者列有序，行有序无序都没关系）
 * 设L = min(matrix) R= max(matrix)  , mid =( L + R ) / 2 ，mid为我们猜测的答案。
 * 然后对于每一行，找它在该行中第几大（也是二分，找上界），累加和K比较。
 * 值得注意的是枚举 答案应该用下界， 因为猜想的解不一定在数组中，不断的收缩直到找到在数组中的元素为止。
 * Time complexity: O(nlogn*log(max – min))
 * Space complexity: O(1)
 */

public class _378_KthSmallestElementinaSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0, j = matrix[0].length - 1; //j为列数目-1
            for (int[] i : matrix) {
                while (j >= 0 && i[j] > mid) j--;
                count += (j + 1); // 该行有多少大于mid，本行越小count越大
            }
            if (count < k) lo = mid + 1; //如果中位数个数小于k(前半段太稀疏)，数量不够，则k在后半段
            else hi = mid; //count过多前半段太密集，则k在前半段
        }
        return lo;
    }
}
