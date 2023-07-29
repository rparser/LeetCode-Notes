package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.*;

public class _3_FindMaximumMaximaCount {
    public static int mostOccuredChar(String s) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        TreeMap<Integer, Set<Character>> countToChars = new TreeMap<>(Collections.reverseOrder());
        Map<Character, Integer> winningTimes = new HashMap<>();

        char[] chars = s.toCharArray(); // Convert the input String to a char[]

        for (char c : chars) {
            int frequency = charFrequency.getOrDefault(c, 0) + 1;
            charFrequency.put(c, frequency);

            // Update the countToChars map for the new frequency
            if (frequency > 1) {
                countToChars.get(frequency - 1).remove(c);
            }
            countToChars.computeIfAbsent(frequency, k -> new HashSet<>()).add(c);

            for (char m : countToChars.firstEntry().getValue()) {
                int winnings = winningTimes.getOrDefault(m, 0) + 1;
                winningTimes.put(m, winnings);
            }
        }
        return winningTimes.values().stream().max(Comparator.comparingInt(a->a)).orElse(0);
    }

    public static void main(String[] args) {
        String str1 = "abaaacbc";
        String str2 = "abaacbcbabacba";
        String str3 = "bccaaacb";
        String str4 = "adbcbcbcc";
        String str5 = "zzzz";
        System.out.println(mostOccuredChar(str1)); // Output: 8 (Character 'a' wins 8 times)
        System.out.println(mostOccuredChar(str2)); // Output: 14 (Character 'a' wins 8 times)
        System.out.println(mostOccuredChar(str3)); // Output: 6 (Character 'c' wins 6 times)
        System.out.println(mostOccuredChar(str4)); // Output: 6 (Character 'b' wins 6 times)
        System.out.println(mostOccuredChar(str5)); // Output: 4 (Character 'z' wins 4 times)
    }
}
