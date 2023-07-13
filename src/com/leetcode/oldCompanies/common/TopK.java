package com.leetcode.oldCompanies.common;

import java.util.*;

import static java.util.stream.Collectors.toMap;

class TopK {
    public static void main(String[] args) {
        char[][] grid = parse(new String[]{
                "L...LL.L",
                "...LLLLL",
                "..L...L.",
                ".LL.LL..",
                "LLLL.L.."
        });
        largest(grid, 3);
    }

    public static char[][] parse(String[] input) {
        char[][] grid = new char[input.length][];
        for (int i = 0; i < input.length; i++) {
            grid[i] = input[i].toCharArray();
        }
        return grid;
    }

    static HashMap<String, Integer> map = new HashMap<>();

    public static void largest(char[][] grid, int K) {
        // two for loops here -> from i to j from 0 to size-1
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                // only count when it's 'L'
                if (grid[i][j] == 'L')
                    dfs(grid, i, j, i, j, map);
        System.out.println("Top " + K + " landmasses are:");
        Map<String, Integer> sorted = map
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        Iterator<Map.Entry<String, Integer>> itr = sorted.entrySet().iterator();
        int i = 0;
        while (itr.hasNext() && i < K) {
            Map.Entry<String, Integer> entry = itr.next();
            System.out.println("Base Tile " + entry.getKey() +
                    ", Size = " + entry.getValue());
            i++;
        }
    }

    // helper method (add a helper method if you could)
    public static void dfs(char[][] grid, int i, int j, int baseI, int baseJ, HashMap<String, Integer> map) {
        if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] == 'L') {
            if (!map.containsKey(baseI + "_" + baseJ)) map.put((baseI + "_" + baseJ), 1);
            else map.put((baseI + "_" + baseJ), map.get(baseI + "_" + baseJ) + 1);
            grid[i][j] = '.';
            dfs(grid, i + 1, j, baseI, baseJ, map);
            dfs(grid, i, j + 1, baseI, baseJ, map);
            dfs(grid, i - 1, j, baseI, baseJ, map);
            dfs(grid, i, j - 1, baseI, baseJ, map);
        }
    }

//     public static void topK (char[][] grid, int i, int j, int baseI, int baseJ, Set<String> Set) {
//    /* "....LL.L",
//       "...LLLLL",
//       "..L...L.",
//       ".LL.LL..",
//       "LLLL.L.."*/
//     // first L is [4,0] then baseI is 4 and baseJ is 0
//       set.add((i-baseI) + "_" + (j-baseJ));

//       // this size-9 landmass will be start with "0_4_0_1_0_3......"
//       //4_0 is the base point, rest will be the shape
//       // first L is [1, 3] "1_3_0_1_-1_0......"
//       // 'L' is [5,8] then we will add -1_-8 (top left points of all tiles)

//       //The baseI and baseJ is the FIRST tile to format this landmass
//   }

    // DFS (traverse  all tiles)
    // PQ/Stack/max to compare with currentIsland track maximum size (follow up with the top K largest landmass)
    // for this follow up we have to take the shape and start-point of the landmass
    // serialize to string the shape and start-point
    // return the maximum size when the DFS is finished
    //

}
