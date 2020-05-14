class Solution {
    public boolean match(String S, int i, int j, String T) {
        for (int k = i; k < j; ++k)
            if (k >= S.length() || S.charAt(k) != T.charAt(k - i))
                return false;
        return true;
    }

    public String addBoldTag(String S, String[] words) {
        int N = S.length();
        boolean[] flag = new boolean[N];
        for (int i = 0; i < N; ++i) {
            // S的起始的字符，每个都要遍历到
            for (String word : words) {
                // S以i起始的字符长度为word.length(),是否和word匹配
                if (!match(S, i, i + word.length(), word)) {
                    // 不匹配则看下一个word
                    continue;
                }
                // 匹配则置标记位
                for (int j = i; j < i + word.length(); ++j) {
                    flag[j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < flag.length; i++) {
            if ((i == 0 && flag[i]) || (flag[i] == true && flag[i - 1] == false)) {
                sb.append("<b>");
            }
            sb.append(S.charAt(i));
            if ((i == flag.length - 1 && flag[i]) || (flag[i] == true && flag[i + 1] == false)) {
                sb.append("</b>");
            }
        }
        return sb.toString();
    }
}