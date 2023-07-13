package com.leetcode.solution;

class _1152_Analyze_User_Website_Visit_Pattern {
//    public int compareTo(List<String> a, List<String> b) {
//        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
//            String s1 = a.get(i);
//            String s2 = b.get(i);
//            for (int j = 0; j < Math.min(s1.length(), s2.length()); j++) {
//                if (s1.charAt(j) != s2.charAt(j)) return s1.charAt(j) - s2.charAt(j);
//            }
//            if (s1.length() != s2.length()) return s1.length() - s2.length();
//        }
//        return a.size() - b.size();
//    }
//
//    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
//        int max = 0;
//        List<String> res = new ArrayList<>();
//        HashMap<String, List<Pair<String, Integer>>> map = new HashMap<>();
//        HashMap<List<String>, HashSet<String>> countMap = new HashMap<>();
//
//        for (int i = 0; i < username.length; i++) {
//            String user = username[i];
//            List<Pair<String, Integer>> list = map.getOrDefault(user, new ArrayList<Pair<String, Integer>>());
//            list.add(new Pair<String, Integer>(website[i], timestamp[i]));
//            map.put(user, list);
//        }
//
//        for (String user : map.keySet()) {
//            List<Pair<String, Integer>> list = map.get(user);
//            list.sort((o1, o2) ->
//                    o1.getValue() - o2.getValue());
//            if (list.size() < 3) continue;
//            for (int i = 0; i < list.size(); i++) {
//                for (int j = i + 1; j < list.size(); j++) {
//                    for (int k = j + 1; k < list.size(); k++) {
//                        List<String> ans = new ArrayList<>();
//                        ans.add(list.get(i).getKey());
//                        ans.add(list.get(j).getKey());
//                        ans.add(list.get(k).getKey());
//                        HashSet<String> set = countMap.getOrDefault(ans, new HashSet<>());
//                        set.add(user);
//                        if (set.size() > max) {
//                            max = set.size();
//                            res = ans;
//                        } else if (set.size() == max) {
//                            if (compareTo(res, ans) > 0) res = ans;
//                        }
//                        countMap.put(ans, set);
//                    }
//                }
//            }
//        }
//        return res;
//    }
}
