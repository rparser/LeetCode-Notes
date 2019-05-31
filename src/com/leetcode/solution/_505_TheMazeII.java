package com.leetcode.solution;

import java.util.*;

public class _505_TheMazeII {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        int[][] dirs = {{-1,0}, {1,0},{0,-1}, {0,1}};

        int[][] mindis = new int[maze.length][maze[0].length];
        for(int i = 0; i < mindis.length; ++i) {
            Arrays.fill(mindis[i], Integer.MAX_VALUE);
        }
        mindis[start[0]][start[1]] = 0;
        while(queue.size() > 0) {
            int[] node = queue.poll();

            for(int k = 0; k < dirs.length; ++k) {
                int nx = node[0] + dirs[k][0];
                int ny = node[1] + dirs[k][1];
                int step = mindis[node[0]][node[1]];

                // loop for all valid moves
                while(nx >= 0 && nx < maze.length && ny >= 0 && ny < maze[0].length && maze[nx][ny] == 0) {
                    nx += dirs[k][0];
                    ny += dirs[k][1];
                    step++;
                }

                // came across the block, step back
                nx -= dirs[k][0];
                ny -= dirs[k][1];
                if(step < mindis[nx][ny]) {
                    mindis[nx][ny] = step;
                    queue.add(new int[]{nx,ny});
                }
            }
        }
        if(mindis[dest[0]][dest[1]] == Integer.MAX_VALUE) {
            return -1;
        }
        return mindis[dest[0]][dest[1]];
    }
}
