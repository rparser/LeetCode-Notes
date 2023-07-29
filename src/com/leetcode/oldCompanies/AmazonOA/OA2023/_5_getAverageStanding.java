package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _5_getAverageStanding {
    public static int[][] averageStandings(int d, int n, int[][] events) {
        Map<Integer, List<int[]>> raceResults = new HashMap<>();

        // Group events by Race
        for (int[] event : events) {
            int raceId = event[0];
            int playerId = event[1];
            int time = event[2];

            raceResults.computeIfAbsent(raceId, k -> new ArrayList<>()).add(new int[]{playerId, time});
        }

        int[] playersGame = new int[d];
        int[] playersResult = new int[d];

        for (List<int[]> race : raceResults.values()) {
            race.sort((a, b) -> {
                if (a[1] != b[1]) {
                    return Integer.compare(a[1], b[1]);
                } else {
                    return Integer.compare(a[0], b[0]);
                }
            });
            for (int i = 0; i < race.size(); i++) {
                playersGame[race.get(i)[0]]++;
                playersResult[race.get(i)[0]] += (i + 1);
            }
        }
        int[][] result = new int[d][2];

        for (int i = 0; i < d; i++) {
            if (playersGame[i] == 0) {
                result[i] = new int[]{-1, -1};
            } else {
                int g = playersGame[i];
                int s = playersResult[i];
                int gcd = calculateGCD(g, s);
                result[i] = new int[]{s / gcd, g / gcd};
//                System.out.println(i + "   " + s/gcd + "    "+g/gcd);
            }
        }
        return result;
    }

    // Helper method to calculate the Greatest Common Divisor (GCD) of two numbers
    private static int calculateGCD(int a, int b) {
        return b == 0 ? a : calculateGCD(b, a % b);
    }

    public static void main(String[] args) {
        int d = 4; // Total number of players
        int n = 4; // Total number of events
        int[][] events = {
                {1, 1, 100},
                {1, 2, 100},
                {2, 3, 200},
                {2, 1, 200},
                {3, 3, 300},
                {3, 2, 400}
        };

        int d2 = 3; // Total number of players
        int n2 = 3; // Total number of events
        int[][] events2 = {
                {1, 1, 100},
                {1, 2, 100},
                {2, 1, 500}
        };


        int d3 = 5; // Total number of players
        int n3 = 5; // Total number of events
        int[][] events3 = {
                {25, 1, 1000},
                {25, 2, 2000},
                {25, 4, 200},
                {30, 2, 90},
                {30, 1, 90},
                {30, 4, 90},
                {35, 1, 50000}
        };

        int[][] result = averageStandings(d, n, events);
        int[][] result2 = averageStandings(d2, n2, events2);
        int[][] result3 = averageStandings(d3, n3, events3);

        for (int i = 0; i < d; i++) {
            System.out.println(result[i][0] + "/" + result[i][1]);
        }
        System.out.println("next one");
        for (int i = 0; i < d2; i++) {
            System.out.println(result2[i][0] + "/" + result2[i][1]);
        }
        System.out.println("next one");
        for (int i = 0; i < d3; i++) {
            System.out.println(result3[i][0] + "/" + result3[i][1]);
        }
    }
}
