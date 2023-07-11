package com.leetcode.solution;

public class _044_Wildcard_Matching {
    // 平均O(mlogn) 渐进O(mn), 空间O(1)
    // p包含?和*
    public boolean isMatchGreedy(String s, String p) {
        int sRight = s.length(), pRight = p.length();
        //先对p串尾做处理，使其以‘*’结尾(因为最后一个*后如果不匹配则必然不匹配)
        while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
            if (s.charAt(sRight - 1) == p.charAt(pRight - 1) || p.charAt(pRight - 1) == '?') {
                sRight--;
                pRight--;
            } else
                return false;
        }
        // p用完了s没有返回false, s也用完了返回true
        if (pRight == 0)
            return sRight == 0;
        //开始从s和p的头处理，xLeft为x的左边界，xTemp为记录已经正确了的位置的x的临时指针
        int sLeft = 0, pLeft = 0;
        int sTemp = -1, pTemp = -1;
        // 用刚才的pRight和sRight
        while (sLeft < sRight && pLeft < pRight) {
            //在p中找‘*’，找到之后，说明部分匹配成功，‘*’后即为新的待匹配字符串,此刻更新s和p的左边界
            if (p.charAt(pLeft) == '*') {
                pLeft++;
                sTemp = sLeft;
                pTemp = pLeft;
                //s和p的对应位置匹配，则更新临时指针，继续往下匹配
            } else if (s.charAt(sLeft) == p.charAt(pLeft) || p.charAt(pLeft) == '?') {
                sLeft++;
                pLeft++;
                //比如p是*def,s是abcdef，这个可以通过，但是目前在p->d,s->a无法通过
                //所以s从left_s和p从left_p的匹配情况已经失败，则需要更新left_s重新匹配
                // 此时只要移动之前记录过的sTemp的位置
                //此处千万不能忽略pTemp != -1的判断！！！！这点十分重要！！！
                // 此判断的意义是：判断p串的是否以‘*’开头，pTemp == -1,则不是以‘*’开头，此时不能更新left_s重新匹配，而是直接失配。

                // 所以必须第一位“*”才能进入这个else if
            } else if (pTemp != -1) { //不能查pLeft否则第一个*if可能超出范围
                sTemp++; //此时sTemp前移再查一次
                sLeft = sTemp;
                pLeft = pTemp;
            } else
                return false;
        }
        //退出while循环，即s和p有一个已经用完，分情况讨论
        //1.都用完，匹配成功
        //2.p用完，s没有用完，因为上面对p尾巴的处理已经让p以‘*’结尾了，所以此种情况也是匹配成功，综合1，2有
        if (pTemp > pRight)
            return true;
        //3.s用完了，p没有，那么此刻p剩下的所有字符都必须是‘*’才以为着匹配成功，否则失败
        return allStars(p, pLeft, pRight);
    }

    public boolean allStars(String str, int left, int right) {
        for (int i = left; i < right; ++i)
            if (str.charAt(i) != '*')
                return false;

        return true;
    }

    // bottom up DP O(MN), O(MN)
    public boolean isMatchDP(String s, String p) {
        int m = s.length(), n = p.length();
        // dp为s前i个字母和n前j个字母能否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        //p为空则必然不匹配
        for (int i = 1; i <= m; i++)
            dp[i][0] = false;
        //s为空，则p必须为连续*才能匹配
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*')
                dp[0][j] = true;
            else
                break;
        }

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                // 如果上一个p不是*，那么此时必然需要&& match才可以
                if (p.charAt(j - 1) != '*')
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
                    // 如果上一个p(j-1)是*，则不使用这个星号就是dp[i][j - 1]，使用这个星号就是dp[i - 1][j]
                else
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];

        return dp[m][n];
    }
}
