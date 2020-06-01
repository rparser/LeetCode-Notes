/**
 * 时间复杂度：\mathcal{O}(N)O(N)。我们只遍历了一次输入元素。
 * 空间复杂度：\mathcal{O}(N)O(N)，输出答案所使用的空间。
 */

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int index = 0, n = intervals.length;
        //找到新区间的放置位置，最后一个右边界小于新区间左边界的旧区间的后面(此时无重叠)
        while (index < n && newInterval[0] > intervals[index][1]) {
            res.add(intervals[index]);
            index++;
        }
        //temp记录合并后新区间的左右边界值
        int[] temp = {newInterval[0], newInterval[1]};
        //如果有重叠
        while (index < n && newInterval[1] >= intervals[index][0]) {
            temp[0] = Math.min(temp[0], intervals[index][0]);
            temp[1] = Math.max(temp[1], intervals[index][1]);
            index++;
        }
        //将合并后的新区间放入结果集
        res.add(temp);
        //将剩余区间放入结果集
        while (index < n) {
            res.add(intervals[index++]);
        }
        return res.toArray(new int[0][]);
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] arr = {{5, 5}, {3, 5}, {-3, -2}, {-5, -3}, {-1, 4}};
        optimalPoints(arr);
    }

    private static int[] optimalPoints(int[][] arr) {
        Arrays.sort(arr, (o1, o2) -> {
            return o1[1] - o2[1];
        });

        List<Integer> listOfPoints = new ArrayList<>();
        int i = 0, n = arr.length;
        while (i < n) {
            int right = arr[i][1];
            listOfPoints.add(right);
            System.out.println(right);
            // BZ: must fix `right` since i is increasing
            while (i < n && arr[i][0] <= right &&
                    right <= arr[i][1])
                i++;
        }

        int[] points = new int[listOfPoints.size()];
        for (int j = 0; j < listOfPoints.size(); j++)
            points[j] = listOfPoints.get(j);
        return points;
    }
}
