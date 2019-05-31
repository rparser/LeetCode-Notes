package com.leetcode.solution;

import java.util.*;

/**
 * 时间复杂度
 * O(MNmax(m,n)) 可能会遍历整个矩阵，同时在每个位置有可能会要遍历最长的长度
 * <p>
 * 空间复杂度
 * O(M*N) queue最大会是整个矩阵的大小
 * 这类题目我习惯用bfs做， 从当前位置开始，每次尝试4个可能的方向，然后遇到1就停止， 把前面的位置加入到queue里面。
 * 这里有一个细节， 传统的bfs，通常会保存一个visited的状态， 然后如果已经访问过的话，会把visited设置成true，然后不加入queue里面，
 * 这对于每次遍历步长一样的操作是可以的， 因为先bfs到的地方，一定是step最小的地方，
 * 但是这个题目不能这样操作，因为每次是直接走到最底下，导致步长不一样， 然后在queue里面先操作到的地方，不一定是step最小的。
 * 所以这个直接填visited是不对的。
 * 我的一个类似的错误代码是直接改maze来标注是否已经被访问（然后在每次访问node的时候，把step传到下一个位置）， 每次bfs加入到queue的时候，直接把maze的值改成2，同样也是不对的。
 * 所以， 必须要建一个mindis的矩阵来保存最小步骤， 然后避免重复访问的逻辑是看新的step是否小于mindis的值
 * <p>
 * 这里遍历也是有坑的地方， 因为遇到边界也需要停下来， 如果直接看下一个是1或者是当前是边界就直接停下来， 就会遇到另外一个方向没有选择的情况。
 * 如果在循环里面加入if(i == 0) break这样的逻辑， 那么如果4个方向单独遍历是没有问题的， 如果合并上下的话，那么可能导致到了编辑以后就不继续遍历了。
 * 改成遍历合法位置（0的位置）， 然后循环退出以后， 往回退一格的话，就会比较清晰简洁， 然后也可以合并4个方向的操作到一个循环里面。
 * <p>
 * 步数mindis数组初始值max。模板while，for四个方向，当在范围内且可到达while走到底，退一步，如果步数更小，更新数组（小于max即已走过）加入队列。返回数组终点步数
 */

public class _505_TheMazeII {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int[][] mindis = new int[maze.length][maze[0].length];
        for (int i = 0; i < mindis.length; ++i) {//初始化最大int
            Arrays.fill(mindis[i], Integer.MAX_VALUE);
        }
        mindis[start[0]][start[1]] = 0;

        while (queue.size() > 0) {
            int[] pos = queue.poll();
            for (int k = 0; k < dirs.length; ++k) {
                int x = pos[0] + dirs[k][0];
                int y = pos[1] + dirs[k][1];
                int step = mindis[pos[0]][pos[1]]; //现有步数

                while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                    x += dirs[k][0];
                    y += dirs[k][1];
                    step++;
                }

                x -= dirs[k][0]; //退一步回到可行的位置
                y -= dirs[k][1];
                if (step < mindis[x][y]) { //小于现有步数则更新
                    mindis[x][y] = step;
                    queue.add(new int[]{x, y});
                }
            }
        }
        if (mindis[dest[0]][dest[1]] == Integer.MAX_VALUE) {
            return -1;
        }
        return mindis[dest[0]][dest[1]];
    }
}
