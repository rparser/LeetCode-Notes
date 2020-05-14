//利用滑窗机制，确定开始位置和结束位置，之间为窗口。
// 先使用数组对s1中出现的字符数进行统计，再使用滑窗遍历s2数组，确定是否符合题目要求。
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 == 0) return true;
        if (len2 < len1) return false;

        char[] ss1 = s1.toCharArray(), ss2 = s2.toCharArray();

        // 对s1中的字符计数，没出现过的字符为-1
        int[] count = new int[26];
        Arrays.fill(count, -1);
        for (char c : ss1) {
            if (count[c - 'a'] == -1)
                count[c - 'a'] = 1;
            else
                count[c - 'a']++;
        }

        // 使用滑窗进行遍历
        int start = 0, end = 0;
        while (start <= len2 - len1) {      // while 1
            while (end < start + len1) {    // while 2

                // 如果对应位置为0，则移动start寻找使其不为0的点作为新起点
                if (count[ss2[end] - 'a'] == 0) {
                    while (count[ss2[end] - 'a'] == 0)
                        count[ss2[start++] - 'a']++;
                    break;
                }

                // 如果对应位置为-1，则移动start至当前end的下一个位置，并令end=start
                if (count[ss2[end] - 'a'] == -1) {
                    while (start < end)
                        count[ss2[start++] - 'a']++;
                    start++;
                    end = start;
                    break;
                }

                // 没有以上两种情况，则对应位置计数-1
                count[ss2[end++] - 'a']--;
            }

            // 如果end==start+len1，则说明while2遍历完成，未被break，因此返回true
            if (end == start + len1)
                return true;
        }
        return false;
    }
}