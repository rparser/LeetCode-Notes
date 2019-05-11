package com.leetcode.AmazonOA;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class FreshDelivery {
    public int fresh(int r, int c, List<List<Integer>> numColumns) {
        if (numColumns.get(0).get(0) == 9) return 0;
        if (r == 0 || c == 0 || numColumns.get(0).get(0) == 0) return -1;
        int[][] steps = new int[r][c]; //走到这里需要的步数
        Queue<int[]> queue = new LinkedList<>();
        int[] start = {0, 0};
        int[] dest = getDest(r, c, numColumns); //找到dest

        queue.add(start);
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; //四个方向

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int k = 0; k < dirs.length; ++k) {
                int x = pos[0] + dirs[k][0];
                int y = pos[1] + dirs[k][1];
                if (x < 0 || x >= r || y < 0 || y >= c || numColumns.get(x).get(y) == 0) //如果不能达到则跳过
                    continue;
                if (numColumns.get(x).get(y) != 0 && (x != 0 || y != 0) && steps[x][y] == 0) { //必须没有走过
                    steps[x][y] = steps[pos[0]][pos[1]] + 1; //之前步数+1
                    System.out.println("pos=" + pos[0] + "    pos[1]=" + pos[1] + "    dest[0]=" + dest[0] + "     dest[1]" + dest[1] + "   steps[pos[0]][pos[1]]=" + steps[dest[0]][dest[1]]);
                    if (x == dest[0] && y == dest[1]) //如果已找到
                        return steps[dest[0]][dest[1]];
                    queue.add(new int[]{x, y}); //加queue
                }
            }
        }
        return -1;
    }

    private int[] getDest(int r, int c, List<List<Integer>> numColumns) {
        int[] dest = new int[2];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (numColumns.get(i).get(j) == 9) {
                    dest[0] = i;
                    dest[1] = j;
                    return dest;
                }
        return dest;
    }


    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.AmazonOA.FreshDelivery");
    }

    @Test
    public void testSolution() {
        List<List<Integer>> area1 = Arrays.asList(Arrays.asList(1, 0, 0), Arrays.asList(1, 0, 0), Arrays.asList(1, 9, 1));
        List<List<Integer>> area2 = Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(1, 0, 0), Arrays.asList(1, 9, 1));
        List<List<Integer>> area3 = Arrays.asList(Arrays.asList(9, 0, 0), Arrays.asList(1, 0, 0), Arrays.asList(1, 9, 1));
        List<List<Integer>> area4 = Arrays.asList(Arrays.asList(1, 1, 1), Arrays.asList(1, 1, 0), Arrays.asList(1, 0, 9));
        List<List<Integer>> area5 = Arrays.asList(Arrays.asList(1, 1, 1), Arrays.asList(1, 1, 1), Arrays.asList(1, 1, 9));
        Assert.assertEquals(3, fresh(3, 3, area1));
        Assert.assertEquals(-1, fresh(3, 3, area2));
        Assert.assertEquals(0, fresh(3, 3, area3));
        Assert.assertEquals(-1, fresh(3, 3, area4));
        Assert.assertEquals(4, fresh(3, 3, area5));
    }
}
