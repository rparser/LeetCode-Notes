class _161_one_edit_distance {
    //O(min(S,T)), O(1)
    public boolean isOneEditDistance(String s, String t) {
        int ls = s.length(), lt = t.length();
        if (Math.abs(ls - lt) > 1)
            return false;
        // s 必须比 t 短或一样
        if (ls > lt)
            return isOneEditDistance(t, s);

        for (int i = 0; i < ls; i++)
            //如果字母不等时
            if (s.charAt(i) != t.charAt(i))
                //如果长度不等，则必须t多一个字母，跳过这个字母后相等
                if (ls != lt)
                    return s.substring(i).equals(t.substring(i + 1));
                // 如果长度相等，必须是最后一个字符 || 跳过这个字母后相等
                else
                    return i == ls - 1 || s.substring(i + 1).equals(t.substring(i + 1));


        return !s.equals(t);
    }
}