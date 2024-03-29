package com.leetcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class _736_Parse_Lisp_Expression {
    public int evaluate(String expression) {
        return solve(expression, new HashMap<>());
    }

    private int solve(String exp, Map<String, Integer> pre_map) {
        String[] exps = getExps(exp);
        if (exps[0].equals("let")) {
            Map<String, Integer> map = new HashMap<>(pre_map);
            for (int i = 1; i < exps.length - 2; i += 2) {
                int v = 0;
                if (exps[i + 1].charAt(0) == '(')
                    v = solve(exps[i + 1], map);
                else
                    v = getValue(map, exps[i + 1]);

                map.put(exps[i], v);
            }
            if (exps[exps.length - 1].charAt(0) == '(')
                return solve(exps[exps.length - 1], map);
            return getValue(map, exps[exps.length - 1]);
        } else {
            int[] res = new int[2];
            for (int i = 1; i <= 2; i++) {
                if (exps[i].charAt(0) == '(')
                    res[i - 1] = solve(exps[i], pre_map);
                else
                    res[i - 1] = getValue(pre_map, exps[i]);
            }
            if (exps[0].equals("add"))
                return res[0] + res[1];
            return
                    res[0] * res[1];
        }
    }

    private int getValue(Map<String, Integer> map, String exp) {
        if (map.containsKey(exp)) return map.get(exp);
        return Integer.parseInt(exp);
    }

    private String[] getExps(String exp) {
        List<String> res = new ArrayList<>();
        char[] strs = exp.substring(1, exp.length() - 1).toCharArray();
        int idx = 0, pre = 0;
        while (idx < strs.length) {
            if (strs[idx] == ' ') {
                res.add(new String(strs, pre, idx - pre));
                pre = idx + 1;
            } else if (strs[idx] == '(') {
                int cnt = 0;
                while (idx < strs.length) {
                    if (strs[idx] == '(')
                        cnt++;
                    else if (strs[idx] == ')')
                        cnt--;

                    idx++;
                    if (cnt == 0)
                        break;
                }
                res.add(new String(strs, pre, idx - pre));
                pre = idx + 1;
            }
            idx++;
        }
        if (pre < strs.length)
            res.add(new String(strs, pre, strs.length - pre));

        return res.toArray(new String[0]);
    }
}