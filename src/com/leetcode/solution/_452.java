//重叠的越多，射箭越少，把重叠的移除，剩下的区间数量就是最低要射的箭。
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        //贪心思想 - 有重叠的气球用一箭
        //将气球按xend升序
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int shotNum = 1;
        int lastEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= lastEnd) continue;//有重叠
            lastEnd = points[i][1];
            shotNum++;
        }
        return shotNum;
    }
}
