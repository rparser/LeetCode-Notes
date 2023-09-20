package com.leetcode.oldCompanies.Angi;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Lant {
    private static List<List<Integer>> calculateAnt(List<List<Boolean>> grid, int steps) {
        int initial = grid.size() / 2;
        int x = initial; //初始坐标
        int y = initial;
        int dir = 0; //初始方向 （0上1右2下3左）
        int[][] walk = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //

        List<List<Integer>> res = new ArrayList<>();
        res.add(List.of(x, y));
        if (steps == 0) return res;
        grid.get(x).set(y, !grid.get(x).get(y)); //翻转初始位置；
        for (int i = 0; i < steps; i++) {
            x += walk[dir][0];
            y += walk[dir][1];
            res.add(List.of(x, y));
            if (grid.get(x).get(y)) { //白
                dir = (dir + 1) % 4;
            } else { //黑
                dir = (dir - 1 + 4) % 4;
            }
            grid.get(x).set(y, !grid.get(x).get(y)); //翻转
        }
        return res;
    }


    public static void main(String[] args) {
        int len = 3, steps = 5;
        List<List<Boolean>> grid = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            List<Boolean> row = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                row.add(true);
            }
            grid.add(row);
        }
        List<List<Integer>> res = calculateAnt(grid, steps);
        for (List<Integer> cur : res) {
            System.out.println("(" + cur.get(0) + "," + cur.get(1) + ")");
        }
    }
}
