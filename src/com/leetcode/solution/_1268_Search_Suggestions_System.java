package com.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _1268_Search_Suggestions_System {
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);

        int len = searchWord.length();
        int n = products.length;
        boolean[] isMatch = new boolean[n];
        Arrays.fill(isMatch, true);
        for (int i = 0; i < len; i++) {
            String temp1 = searchWord.substring(0, i + 1);
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (!isMatch[j]) {
                    continue;
                }
                int curr_len = products[j].length();
                String temp2 = "";
                if (i < curr_len) {
                    temp2 = products[j].substring(0, i + 1);
                } else {
                    temp2 = products[j];
                }

                if (temp1.equals(temp2)) {
                    temp.add(products[j]);
                } else {
                    isMatch[j] = false;
                    continue;
                }
                if (temp.size() == 3) {
                    break;
                }
            }
            ans.add(new ArrayList<>(temp));
        }
        return ans;
    }
}