package com.leetcode.solution;

/**
 * DFS染色问题，只染同样原始颜色的
 * <p>
 * Time complexity: O(mn)
 * Space complexity: O(1)
 * 步骤一：首先要判断给定的元素位置是否在数组边界内，并且给定的newColor值是否与给定位置元素值相同，如果相同，则直接返回给定的数组image
 * 步骤二：在递归方法中，没有返回值，如果递归到的元素值与初始给的位置的元素值不同，则返回，否则将其值改为newColor，然后通过递归调用更改其四邻域的值
 * <p>
 * 模板dfs,void(r,c,old,new)函数，当前点不为oldcolor返F,否则修改为newcolor，四方向递归
 */

public class _733_Flood_Fill {
    private void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        if (image == null || r >= image.length || c >= image[0].length || r < 0 || c < 0
                || image[r][c] != oldColor) //只染同样原始颜色的
            return;

        image[r][c] = newColor;
        dfs(image, r - 1, c, oldColor, newColor);
        dfs(image, r, c - 1, oldColor, newColor);
        dfs(image, r + 1, c, oldColor, newColor);
        dfs(image, r, c + 1, oldColor, newColor);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || sr >= image.length || sc >= image[0].length) return image;
        if (image[sr][sc] == newColor) return image; //超出范围或不需染色则返回

        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
}
