class Solution {
    public boolean isInterleaveDP(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                } else {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                            || dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                }
            }
        }
        return dp[s2.length()];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(0, 0));
        //判断是否已经遍历过
        boolean[][] visited = new boolean[s1.length() + 1][s2.length() + 1];
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            //到达右下角就返回 true
            if (cur.x == s1.length() && cur.y == s2.length()) {
                return true;
            }
            // 尝试是否能向右走
            int right = cur.x + 1;
            if (right <= s1.length() && s1.charAt(right - 1) == s3.charAt(right + cur.y - 1)) {
                if (!visited[right][cur.y]) {
                    visited[right][cur.y] = true;
                    queue.offer(new Point(right, cur.y));
                }
            }
            // 尝试是否能向下走
            int down = cur.y + 1;
            if (down <= s2.length() && s2.charAt(down - 1) == s3.charAt(down + cur.x - 1)) {
                if (!visited[cur.x][down]) {
                    visited[cur.x][down] = true;
                    queue.offer(new Point(cur.x, down));
                }
            }
        }
        return false;
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}