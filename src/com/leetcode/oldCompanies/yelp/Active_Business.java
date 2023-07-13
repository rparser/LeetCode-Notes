package com.leetcode.oldCompanies.yelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Active_Business {
    public List<Integer> active(List<List<String>> input) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        double avgAds = 0, avgPage = 0, avgPhoto = 0, avgReview = 0;
        int countAds = 0, countPage = 0, countPhoto = 0, countReview = 0;
        for (List<String> temp : input) {
            int id = Integer.parseInt(temp.get(2));
            int value = Integer.parseInt(temp.get(1));
            map.putIfAbsent(id, new ArrayList<>());
            List<Integer> tempList = map.get(id);
            switch (temp.get(0)) {
                case "ads": {
                    int tempValue = tempList.get(0);
                    tempList.set(0, tempValue + value);
                    map.put(id, tempList);
                    avgAds += value;
                    countAds++;
                }
                case "page_views": {
                    int tempValue = tempList.get(1);
                    tempList.set(1, tempValue + value);
                    map.put(id, tempList);
                    avgPage += value;
                    countPage++;
                }
                case "photo_views": {
                    int tempValue = tempList.get(2);
                    tempList.set(2, tempValue + value);
                    map.put(id, tempList);
                    avgPhoto += value;
                    countPhoto++;
                }
                case "reviews": {
                    int tempValue = tempList.get(3);
                    tempList.set(3, tempValue + value);
                    map.put(id, tempList);
                    avgReview += value;
                    countReview++;
                }
            }
        }
        avgAds /= countAds;
        avgPage /= countPage;
        avgPhoto /= countPhoto;
        avgReview /= countReview;

        return null;
    }
}
