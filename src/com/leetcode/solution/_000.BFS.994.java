public class _994_RottingOranges {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count_fresh = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j}); //加入坏队列
                } else if (grid[i][j] == 1) {
                    count_fresh++; //统计好番茄
                }
            }
        }

        if (count_fresh == 0) return 0; //如果没有好的
        int count = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        //bfs starting from initially rotten oranges
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) { //BFS 检查每一层
                int[] point = queue.poll(); //取出rotten
                for (int dir[] : dirs) { //遍历四个方向
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    //超出范围，或空值，或已坏，不处理
                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2) continue;
                    grid[x][y] = 2; //否则变坏
                    queue.offer(new int[]{x, y}); //把坏的加入队列
                    count_fresh--; //好番茄-1
                }
            }
        }
        return count_fresh == 0 ? count - 1 : -1; //如果还剩好番茄则返回-1,count-1是因为最后一步无效
    }
}

//DP//322
/**
 * DP换零钱
 * 思路：-  use dp bottom up, memoization method
 * - we know each solution is formed by at least 1 existing coin, so we check if dp[i-coin] exists.
 * - keep filling dp[i] with the min
 * Complexity: Time O(m*n) -  m is amount, n is len of coins,  Space O(m) - dp array
 * <p>
 * 单array操作， 根据上一次的运算，来fill 一个array, return the last element of array
 * DP,2loop(0-amount,coin),count=-1,i>=coin&dp[i-coin]!=-1上一步可实现，则cur=上一步+1，
 * 如果count==-1||cur<count,更新count=cur再赋给dp
 */

public class _322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; //0,1,2,..,amount
        for (int i = 1; i <= amount; i++) {
            int count = -1;
            //key is min count, all solutions must be generated from existing coin
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != -1) { //skip non-existing solution
                    int cur = dp[i - coin] + 1; //重点，前一个+1
                    if (count == -1 || count > cur) count = cur; //如果之前无法达到，或当前cur更小
                }
            }
            dp[i] = count; //如果最后不能达到，会把-1加入dp,否则加入cur
        }
        return dp[amount];
    }
}
