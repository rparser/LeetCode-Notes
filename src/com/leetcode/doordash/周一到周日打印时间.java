package com.leetcode.doordash;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 周一到周日打印时间 {
    public static Map<String, Integer> dayToInt;
    public static void main(String[] args) throws ParseException {
        String startTimeString = "sun 11:32 pm";
        String endTimeString = "tue 12:53 am";
        List<String> timeIntervals = new ArrayList<>();

        dayToInt = new HashMap<>();
        dayToInt.put("mon", 1);
        dayToInt.put("tue", 2);
        dayToInt.put("wed", 3);
        dayToInt.put("thu", 4);
        dayToInt.put("fri", 5);
        dayToInt.put("sat", 6);
        dayToInt.put("sun", 7);

        int startDay = dayToInt.get(startTimeString.substring(0, 3));
        int endDay = dayToInt.get(endTimeString.substring(0, 3));

        int startTime = convertTime(startTimeString.substring(4, 12));
        int endTime = convertTime(endTimeString.substring(4, 12));

        int start = startDay * 10000 + startTime;
        int end = endDay * 10000 + endTime;

        if (start % 5 != 0) {
            start = start + (5 - start % 5);
        }
        if (end % 5 != 0) {
            end = end - end % 5;
        }

        timeIntervals.add(String.valueOf(start));
        System.out.println("START " + start);
        System.out.println("END " + end);
        while (start != end) {
            start += 5;
            if (start % 100 == 60) {
                start = start + 100 - 60;
            }
            if (start / 100 % 100 == 24) {
                if (start / 10000 == 7) {
                    start = start - 62400;
                } else {
                    start = start + 10000 - 2400;
                }
            }
            timeIntervals.add(String.valueOf(start));
        }
        System.out.println(timeIntervals);

    }

    private static int convertTime(String str) {
        int intHour;

        String hour = str.substring(0, 2);
        int minute = Integer.parseInt(str.substring(3, 5));
        boolean pm = str.contains("pm");
        if (!hour.equals("12") && pm) {
            intHour = Integer.parseInt(hour) + 12;
        } else if (hour.equals("12") && !pm) {
            intHour = Integer.parseInt(hour) - 12;
        } else {
            intHour = Integer.parseInt(hour);
            System.out.println(hour);
        }
        return intHour * 100 + minute;
    }
}