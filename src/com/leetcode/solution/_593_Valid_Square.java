package com.leetcode.solution;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.*;

/**
 * 有效正方形
 * Time complexity : O(1)O(1). A fixed number of comparisons are done.
 * Space complexity : O(1)O(1). No extra space required.
 * <p>
 * 计算list中有效的正方形
 */

/**
 * x2y2     x3y3
 * _________
 * |        |
 * |        |
 * |        |
 * |        |
 * |________|
 * x0y0     x1y1
 * <p>
 * (y10/x10)*(y20/x20)=-1
 * y10^2+x10^2=y20^2+x20^2
 * <p>
 * y10/x10=x02/y20
 * x02=y10*y20/x10
 * y10^2+x10^2=y20^2+(y10^2*y20^2/x10^2)
 * y10^2+x10^2=(y20^2*x10^2/x10^2)+(y10^2*y20^2/x10^2)
 * y10^2+x10^2=(y20^2*x10^2+y10^2*y20^2)/x10^2
 * y10^2+x10^2=(y20^2)*(x10^2+y10^2)/x10^2
 * y20^2=x10^2
 * y20=x10 or x01, y2-y0=x1-x0 ,y2=x1-x0+y0 or y2=x0-x1+y0
 * Similarly，x20=y01 or y10,x2=y0-y1+x0 or x2=y1-y0+x0
 * y31=x01 or x10 -> y3=x0-x1+y1 or y3=x1-x0+y1 //顺序相反
 * x31=y10 or y01 ->  x3=y1-y0+x1 or x3=y0-y1+x1
 */

public class _593_Valid_Square {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashMap<Double, Integer> map = new HashMap<>(); //距离，存在的个数
        double dist12 = dist(p1, p2);
        double dist13 = dist(p1, p3);
        double dist14 = dist(p1, p4);
        double dist23 = dist(p2, p3);
        double dist24 = dist(p2, p4);
        double dist34 = dist(p3, p4);

        map.put(dist12, map.getOrDefault(dist12, 0) + 1);
        map.put(dist13, map.getOrDefault(dist12, 0) + 1);
        map.put(dist14, map.getOrDefault(dist12, 0) + 1);
        map.put(dist23, map.getOrDefault(dist12, 0) + 1);
        map.put(dist24, map.getOrDefault(dist12, 0) + 1);
        map.put(dist34, map.getOrDefault(dist12, 0) + 1);

        if ((map.size() == 2)  //必须只能有两个长度
                && ((map.get(dist12) == 4 && dist13 / dist12 * dist14 / dist12 * dist23 / dist12 * dist24 / dist12 * dist34 / dist12 == 4) //12为四周的边
                || (map.get(dist12) == 2 && dist12 / dist13 * dist12 / dist14 * dist12 / dist23 * dist12 / dist24 * dist12 / dist34 == 16))) //12为对角线
            return true;
        return false;//不是两个长度就返回false
    }

    private double dist(int[] p1, int[] p2) { //didn't calculate square root
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }

    public List allValidSquare(List<int[]> input) {
        HashMap<String, Integer> map = new LinkedHashMap<>(); //位置，个数
        for (int i = 0; i < input.size(); i++) {
//            if(mapX.containsKey(input.get(i)[0]))
//                mapX.get(input.get(i)[0]).add(i);
//            else{
//                mapX.put(input.get(i)[0],new ArrayList<>());
//                mapX.get(input.get(i)[0]).add(i);
//            }
//
//            if(mapY.containsKey(input.get(i)[0]))
//                mapY.get(input.get(i)[0]).add(i);
//            else{
//                mapY.put(input.get(i)[0],new ArrayList<>());
//                mapY.get(input.get(i)[0]).add(i);
//            }
//            if (map.containsKey(input.get(i)[0] + "," + input.get(i)[1]))
//                map.get(input.get(i)[0] + "," + input.get(i)[1]);
//            else {
//                map.put(input.get(i)[0] + "," + input.get(i)[1], i);
//                map.get(input.get(i)[0] + "," + input.get(i)[1]);
//            }
            map.put(input.get(i)[0] + "," + input.get(i)[1], i);
        }
        Set<String> result = new HashSet<>();
        for (int i = 0; i < input.size(); i++) { //n^2复杂度
            for (int j = i + 1; j < input.size(); j++) {
                int x0 = input.get(i)[0];
                int y0 = input.get(i)[1];
                int x1 = input.get(j)[0];
                int y1 = input.get(j)[1];
                int x2 = (y0 - y1) + x0;
                int y2 = (x1 - x0) + y0;
                int x3 = (y0 - y1) + x1;
                int y3 = (x1 - x0) + y1;
                for (String s : map.keySet()) System.out.println(s);
                if (map.containsKey(x2 + "," + y2) && map.containsKey(x3 + "," + y3) && map.get(x2 + "," + y2) != i
                        && map.get(x2 + "," + y2) != j && map.get(x3 + "," + y3) != i && map.get(x3 + "," + y3) != j) {
                    int[] arr = new int[]{i, j, map.get(x2 + "," + y2), map.get(x3 + "," + y3)};
                    Arrays.sort(arr);
                    StringBuilder curFind = new StringBuilder();
                    for (int k : arr) curFind.append(",").append(k);
                    result.add(curFind.toString());
                }
                x2 = -(y0 - y1) + x0;
                y2 = -(x1 - x0) + y0;
                x3 = -(y0 - y1) + x1;
                y3 = -(x1 - x0) + y1;
                if (map.containsKey(x2 + "," + y2) && map.containsKey(x3 + "," + y3) && map.get(x2 + "," + y2) != i
                        && map.get(x2 + "," + y2) != j && map.get(x3 + "," + y3) != i && map.get(x3 + "," + y3) != j)   {
                    int[] arr = new int[]{i, j, map.get(x2 + "," + y2), map.get(x3 + "," + y3)};
                    Arrays.sort(arr);
                    StringBuilder curFind = new StringBuilder();
                    for (int k : arr) curFind.append(",").append(k);
                    result.add(curFind.toString());
                }
            }
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._593_ValidSquare");
    }

    @Test
    public void testSolution() {
        List<int[]> input = new ArrayList<>();
        input.add(new int[]{1134, -2539});
        input.add(new int[]{-792, -1897});
        input.add(new int[]{492, -1255});
        input.add(new int[]{492, -1255});
        input.add(new int[]{-150, -3181});
        Assert.assertEquals(new ArrayList<>(), allValidSquare(input));
    }
}
