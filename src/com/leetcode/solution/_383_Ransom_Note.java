package com.leetcode.solution;

public class _383_Ransom_Note {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            arr[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            int c = ransomNote.charAt(i) - 'a';
            arr[c]--;
            if (arr[c] < 0) {
                return false;
            }
        }
        return true;
    }
}
