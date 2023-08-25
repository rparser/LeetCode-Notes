package com.leetcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _811_Subdomain_Visit_Count {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> counts = new HashMap<>();
        for (String domain : cpdomains) {
            String[] cpinfo = domain.split("\\s+"); //0 is count, 1 is domain
            int count = Integer.parseInt(cpinfo[0]);
            String[] frags = cpinfo[1].split("\\."); //domain name
            String cur = "";
            for (int i = frags.length - 1; i >= 0; --i) {
                cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
                counts.put(cur, counts.getOrDefault(cur, 0) + count);
            }
        }

        List<String> result = new ArrayList<>();
        for (String dom : counts.keySet())
            result.add("" + counts.get(dom) + " " + dom);
        return result;
    }
}
