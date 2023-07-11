package com.leetcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 可以合成Palindrome Pairs有几种情况:
 * 1. ["abc", "cba"]
 * 2. ["zyzbc", "cb"]
 * 3. ["cbzyz", "bc"]
 * <p>
 * 要么有个当前string的reverse过来的string也存在，要么当前string的左半部分或者右半部分已经是palindrome, 剩下部分有reverse过来的string存在.
 * 先用HashMap把原有string 和对应index保存。然后对于每一个string拆成left 和 right两个substring, 若是其中一个substring本身就是palindrom, 就看另一个substring的reverse是不是存在.
 * 当然""也是palindrome, 所以如果左右有""存在, 那就是看left, right本身有没有对应的reverse存在.
 * Note: 要注意["abc", "cba"], 一个substring为""的情况只检查一遍. 不然先检查"abc", left = "", right = "abc", 或者right = "", left = "abc", reverse都存在，
 * 就会加[0,1], [1,0]. 等再检查 "cba"时 又会重复加一遍结果. 所以第二个check时要加上right.length() != 0.
 * Time Complexity: O(n * len), n = words.length, len时word的平均长度.
 * Space: O(n), regardless res.
 */

public class _336_Palindrome_Pairs {
    // O(n * len), O(n)
    // 三种可能 1. 倒序， 倒序， 2.回文+倒序， 倒序 3. 倒序+回文，倒序
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) return result;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++)
            map.put(words[i], i);

        for (int i = 0; i < words.length; i++) {
            String s = words[i]; //把单词分左右

            for (int j = 0; j <= s.length(); j++) { //j是能到word[i].length()的
                String left = s.substring(0, j);
                String right = s.substring(j);

                //如果左侧palindrome, 找右侧
                if (isPalindrome(left)) {
                    // 找右侧对应的部分,包括完全倒序
                    String reversedRight = new StringBuilder(right).reverse().toString();
                    // 找右侧且不是自身, 加入结果
                    if (map.containsKey(reversedRight) && map.get(reversedRight) != i) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(map.get(reversedRight));
                        list.add(i);
                        result.add(list);
                    }
                }

                //如果右侧palindrome, 找左侧
                if (isPalindrome(right)) {
                    // 找左侧对应的部分
                    String reversedLeft = new StringBuilder(left).reverse().toString();
                    // 找右侧且不是自身(而且right.length()不能为0，因为之前已处理过), 加入结果
                    if (map.containsKey(reversedLeft)
                            && map.get(reversedLeft) != i
                            && right.length() != 0) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(reversedLeft));
                        result.add(list);
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
            if (s.charAt(i) != s.charAt(j))
                return false;

            i++;
            j--;
        }
        return true;
    }
}
