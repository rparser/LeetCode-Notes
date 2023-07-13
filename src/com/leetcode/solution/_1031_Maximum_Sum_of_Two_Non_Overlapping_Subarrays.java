package com.leetcode.solution;

class _1031_Maximum_Sum_of_Two_Non_Overlapping_Subarrays {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int res = 0, Lsum = 0, Lmax = 0, Msum = 0, Mmax = 0;
        for (int i = 0; i < A.length; ++i) {
            Msum += A[i];
            if (i - M >= 0) Msum -= A[i - M];
            if (i - M >= 0) Lsum += A[i - M];
            if (i - M - L >= 0) Lsum -= A[i - L - M];
            Lmax = Math.max(Lmax, Lsum);
            res = Math.max(res, Lmax + Msum);
        }
        Lsum = Msum = 0;
        for (int i = 0; i < A.length; ++i) {
            Lsum += A[i];
            if (i - L >= 0) Lsum -= A[i - L];
            if (i - L >= 0) Msum += A[i - L];
            if (i - M - L >= 0) Msum -= A[i - L - M];
            Mmax = Math.max(Mmax, Msum);
            res = Math.max(res, Mmax + Lsum);
        }
        return res;
    }
}
