import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 利用滑窗机制，确定开始位置和结束位置，之间为窗口。
 * 先使用数组对s1中出现的字符数进行统计，再使用滑窗遍历s2数组，确定是否符合题目要求。
 * 时间复杂度：O(l1 + (l2 - l1))
 **/
class Solution {
    // s1是短的pattern
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 == 0) return true;
        if (len2 < len1) return false;

        // 对s1中的字符计数，没出现过的字符为-1
        int[] count = new int[26];
        Arrays.fill(count, -1);
        for (char c : s1.toCharArray()) {
            if (count[c - 'a'] == -1)
                count[c - 'a'] = 1;
            else
                count[c - 'a']++;
        }

        char[] chars = s2.toCharArray();
        // 使用滑窗进行遍历
        int left = 0, right = 0;
        while (left <= len2 - len1) {      // while 1
            while (right < left + len1) {    // while 2
                // 如果end -1说明不可能，则需要把每个字母重置
                if (count[chars[right] - 'a'] == -1) {
                    while (left < right) {
                        count[chars[left] - 'a']++;
                        left++;
                    }
                    // 重置后,各向前进一步
                    left++;
                    right++;
                    break;
                }

                // 如果end为0，说明有但用光，找到最左第一个是end字母的替换，继续遍历
                if (count[chars[right] - 'a'] == 0) {
                    while (count[chars[right] - 'a'] == 0) {
                        count[chars[left] - 'a']++;
                        left++;
                    }
                    break;
                }
                // 没有以上两种情况，则对应位置计数-1
                count[chars[right] - 'a']--;
                right++;
            }

            // 如果end==start+len1，则说明while2遍历完成，未被break，因此返回true
            if (right == left + len1)
                return true;
        }
        return false;
    }


    public boolean checkInclusionMap(String pattern, String str) {
        int left = 0, matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (int right = 0; right < str.length(); right++) {
            char rightChar = str.charAt(right);
            if (charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0) // character is completely matched
                    matched++;
            }

            if (matched == charFrequencyMap.size())
                return true;

            if (right >= pattern.length() - 1) { // shrink the window by one character
                char leftChar = str.charAt(left);
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap.get(leftChar) == 0)
                        matched--; // before putting the character back, decrement the matched count
                    // put the character back for matching
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
                left++;
            }
        }
        return false;
    }
}