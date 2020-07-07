package com.leetcode.solution;

class _1360_number_of_days_between_two_dates {
    int[] _365M = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int daysBetweenDates(String date1, String date2) {
        int[] dates1 = parseDate(date1), dates2 = parseDate(date2);
        return Math.abs(calculateDays(dates1[0], dates1[1], dates1[2]) - calculateDays(dates2[0], dates2[1], dates2[2]));
    }

    int calculateDays(int year, int month, int day) {
        int dayC1 = day - 1;
        for (int i = month; i > 1; i--)
            dayC1 += _365M[i - 1 - 1];
        if ((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month > 2)
            dayC1++;
        dayC1 += (365 * (year - 1) + (year - 1) / 4 - (year - 1) / 100 + (year - 1) / 400);
        return dayC1;
    }

    int[] parseDate(String date) {
        return new int[]{Integer.parseInt(date.substring(0, 4))
                , Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10))};
    }
}