package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.*;

public class _4_FindUniqueValues {
    public static int findUniqueValues(List<Integer> skills) {
        int n = skills.size();
        Collections.sort(skills);
        Set<Double> uniqueMValues = new HashSet<>();

        // Pair the most skilled and least skilled workers until there are no workers left
        for (int i = 0; i < n / 2; i++) {
            int maxSkill = skills.get(n - i - 1);
            int minSkill = skills.get(i);
            double averageSkill = (maxSkill + minSkill) / 2.0;
            uniqueMValues.add(averageSkill);
        }

        return uniqueMValues.size();
    }

    public static void main(String[] args) {

        List<Integer> skills1 = Arrays.asList(5, 7, 3, 2); // Output: 3 (unique m values: 6, 5, 3)
        List<Integer> skills2 = Arrays.asList(10, 2, 8, 4, 6, 12); // Output: 5 (unique m values: 6, 7, 8, 5, 10)
        List<Integer> skills3 = Arrays.asList(1, 4, 1, 3, 5, 6); // Output: 5 (unique m values: 6, 7, 8, 5, 10)
        List<Integer> skills4 = Arrays.asList(1, 1, 1, 1, 1, 1); // Output: 5 (unique m values: 6, 7, 8, 5, 10)
        List<Integer> skills5 = Arrays.asList(1, 100, 10, 1000); // Output: 5 (unique m values: 6, 7, 8, 5, 10)

        System.out.println(findUniqueValues(skills1));
        System.out.println(findUniqueValues(skills2));
        System.out.println(findUniqueValues(skills3));
        System.out.println(findUniqueValues(skills4));
        System.out.println(findUniqueValues(skills5));
    }
}
