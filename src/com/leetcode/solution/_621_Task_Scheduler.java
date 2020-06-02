class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        // 做成表格，每行宽度为(n+1)，长度为最频繁的字母数
        // count是最后一行有多少并列,也就是统计有多少并列最多
        int max = 0, count = 0;
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > max) {
                max = map.get(c);
                count = 0;
            }
            if (map.get(c) == max) count++;
        }
        int v1 = (n + 1) * (max - 1) + count;
        int v2 = tasks.length;
        return Math.max(v1, v2);
    }
}