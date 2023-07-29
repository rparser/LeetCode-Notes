package com.leetcode.oldCompanies.AmazonOA.OA2023;

public class _19_Password {
    public static boolean isSubsequence(String oldPassword, String newPassword) {
        char[] oldChars = oldPassword.toCharArray();
        char[] newChars = newPassword.toCharArray();
        int i = 0; // Pointer for oldPassword
        int j = 0; // Pointer for newPassword

        while (i < oldChars.length && j < newChars.length) {
            if (oldChars[i] == newChars[j] || oldChars[i] == newChars[j] + 1
                    || (oldChars[i] == 'a' && newChars[j] == 'z')) {
                i++;
            }
            j++;
        }

        return i == oldChars.length;
    }

    public static void main(String[] args) {
        String newPassword = "baacbab";
        String oldPassword = "abdbc";
        System.out.println(isSubsequence(oldPassword, newPassword)); // Output: true
        String newPassword2 = "zzzzzzz";
        String oldPassword2 = "aaaaa";
        System.out.println(isSubsequence(oldPassword2, newPassword2)); // Output: true
    }
}
