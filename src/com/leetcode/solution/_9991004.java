//双指针,right不断向右扩大窗口以获取最长的连续1，直至0的个数超过K, left再向右移动缩小窗口以满足要求计算一次候选值

class Solution {
    public int longestOnes(int[] A, int K) {
        int left = 0, right = 0, count = 0, res = 0;
        while (right < A.length){
            count = A[right++] == 0 ? count + 1 : count;
            while (count > K){
                count = A[left++] == 0 ? count - 1 : count;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}
