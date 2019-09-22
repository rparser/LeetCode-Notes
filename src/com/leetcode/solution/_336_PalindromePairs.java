package com.leetcode.solution;

import java.util.*;

/**
 * 可以合成Palindrome Pairs有几种情况:
 * 1. ["abc", "cba"]
 * 2. ["aabc", "cb"]
 * 3. ["cbaa", "bc"]
 * <p>
 * 要么有个当前string的reverse过来的string也存在，要么当前string的左半部分或者右半部分已经是palindrome, 剩下部分有reverse过来的string存在.
 * 先用HashMap把原有string 和对应index保存。然后对于每一个string拆成left 和 right两个substring, 若是其中一个substring本身就是palindrom, 就看另一个substring的reverse是不是存在.
 * 当然""也是palindrome, 所以如果左右有""存在, 那就是看left, right本身有没有对应的reverse存在.
 * Note: 要注意["abc", "cba"], 一个substring为""的情况只检查一遍. 不然先检查"abc", left = "", right = "abc", 或者right = "", left = "abc", reverse都存在，
 * 就会加[0,1], [1,0]. 等再检查 "cba"时 又会重复加一遍结果. 所以第二个check时要加上right.length() != 0.
 * Time Complexity: O(n * len), n = words.length, len时word的平均长度.
 * Space: O(n), regardless res.
 */

public class _336_PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) return result;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) map.put(words[i], i);

        for (int i = 0; i < words.length; i++) {
            String s = words[i]; //把单词分左右

            for (int j = 0; j <= s.length(); j++) { //j是能到word[i].length()的
                String left = s.substring(0, j);
                String right = s.substring(j);

                //if left part is palindrome, find reversed right part
                if (isPalindrome(left)) {
                    String reversedRight = new StringBuilder(right).reverse().toString();
                    if (map.containsKey(reversedRight) && map.get(reversedRight) != i) { //找到且不是本身
                        ArrayList<Integer> l = new ArrayList<>();
                        l.add(map.get(reversedRight));
                        l.add(i);
                        result.add(l);
                    }
                }

                //if right part is a palindrome, find reversed left part
                if (isPalindrome(right)) {
                    String reversedLeft = new StringBuilder(left).reverse().toString();
                    if (map.containsKey(reversedLeft)
                            && map.get(reversedLeft) != i
                            && right.length() != 0) {
                        //make sure right is not "", already handled in the if above
                        ArrayList<Integer> l = new ArrayList<>();
                        l.add(i);
                        l.add(map.get(reversedLeft));
                        result.add(l);
                    }
                }
            }
        }
        return result;
    }

    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
