package com.leetcode.oldCompanies.common;

import com.leetcode.oldCompanies.Angi.LangtonsAnt;

import java.util.*;
import java.util.stream.Collectors;

public class BestGrade {
    public static void main(String[] args) {
        String student[][] = {{"jerry", "65"}, {"bob", "1"}, {"Wang", "94"}, {"Li", "94"}, {"jerry", "23"}, {"jerry", "23"}, {"jerry", "100"}, {"Eric", "83"}};

        HashMap<String, Integer> scoreMap = new HashMap<>();
        HashMap<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < student.length; i++) {
            scoreMap.put(student[i][0], scoreMap.getOrDefault(student[i][0], 0) + Integer.parseInt(student[i][1]));
            countMap.put(student[i][0], countMap.getOrDefault(student[i][0], 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : scoreMap.entrySet())
            entry.setValue(entry.getValue() / countMap.get(entry.getKey()));
        Optional a = scoreMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .findFirst(); //不存在等同

        List c = scoreMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList()); //存在等同
//            System.out.println(a.get().toString().substring(3));
        System.out.println(a.get().toString().split("=")[0]);
        System.out.println(a.get().toString().split("=")[1]);
        System.out.println(c.toString());
        System.out.println(c.get(2).toString());


        Map<String, Integer> map = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }

        //这里将map.entrySet()转换成list
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        //然后通过比较器来实现排序
//        Collections.sort(list);
//        for (Map.Entry<String, String> mapping : list) {
//            System.out.println(mapping.getKey() + ":" + mapping.getValue());
//        }

        class SortGrade<V extends Comparable<V>> implements Comparator<Map.Entry<?, V>> {
            public int compare(Map.Entry<?, V> o1, Map.Entry<?, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        }
    }
}
