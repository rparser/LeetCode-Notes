//时间复杂度 : 由于是标准的二分查找，时间复杂度为O(\log(m n))O(log(mn))。
//        空间复杂度 : O(1)O(1)。
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 解法：一次二分查找
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int row = matrix.length;
        int col = matrix[0].length;
        // 可以理解为二维数组转换为一个一维数组，right就是最后一个元素。
        int left = 0, right = row * col - 1;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            // 重点：借助行数，将一维的索引值转换回二维坐标 行：mid / col, 列：mid % col。
            // 如果这一点比target小，那么mid和mid左边的值都不可能是解
            if (matrix[mid / col][mid % col] < target) {
                // 将区间转换为[mid + 1, right]
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        //判断最后结束位置是不是target，如果是则true，不是则false。
        if (matrix[left / col][left % col] != target) return false;
        return true;
    }
}
