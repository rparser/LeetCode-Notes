package com.leetcode.oldCompanies.AmazonOA.OA2023;

public class _18_regex {
    public static int getMinWildcard(String[] patterns) {
        int n = patterns[0].length();
        int minWildcards = 0;
        for (int i = 0; i < n; i++) {
            char c = patterns[0].charAt(i);
            for (int j = 1; j < patterns.length; j++) {
                char t = patterns[j].charAt(i);
                if (c == '?' && t != '?') {
                    c = t;
                } else if (c != '?' && t != '?' && c != t) {
                    System.out.println(c + "    "+ t);
                    System.out.println(i + "    "+ j);
                    minWildcards++;
                    break;
                }
            }
        }
        return minWildcards;
    }

    public static void main(String[] args) {
        String[] patterns = {"ha???rrank", "?a?ke?bank"};
        int minWildcards = getMinWildcard(patterns);
        System.out.println("Minimum wildcard characters: " + minWildcards); // Output: 1
    }
}
