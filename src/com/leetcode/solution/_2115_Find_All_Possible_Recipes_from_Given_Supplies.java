package com.leetcode.solution;

import java.util.*;

public class _2115_Find_All_Possible_Recipes_from_Given_Supplies {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> result = new ArrayList<>();
        Map<String, List<String>> adjVex = new HashMap<>(); // 某个ingredient可以做的食谱
        Map<String, Integer> inDegree = new HashMap<>(); // 某个recipe剩余多少个ingredient

        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            for (String igd : ingredients.get(i)) {
                adjVex.putIfAbsent(igd, new ArrayList<>());
                adjVex.get(igd).add(recipe);
                inDegree.put(recipe, inDegree.getOrDefault(recipe, 0) + 1);
            }
        }

        Queue<String> queue = new LinkedList<>();
        for (String supply : supplies) {
            queue.offer(supply);
        }
        while (!queue.isEmpty()) {
            String supply = queue.poll();
            for (String recipe : adjVex.getOrDefault(supply, new ArrayList<>())) {
                inDegree.put(recipe, inDegree.get(recipe) - 1);
                if (inDegree.get(recipe) == 0) { // 处理所有已不需要ingredient的recipe
                    queue.offer(recipe);
                    result.add(recipe);
                }
            }
        }
        return result;
    }
}
