package com.leetcode.doordash;

import java.util.*;

public class ClosestCity {
    public static void main(String[] args) {
        String[] names = new String[]{"p1", "p2", "p3", "p4"};
        int[] xs = new int[]{0, 90, 10, 0};
        int[] ys = new int[]{100, 0, 10, 0};
        String[] queries = new String[]{"p1", "p2", "p3", "p4"};
        // String[] names = new String[] {"p1","p2","p3","p4","p5"};
        // int[] xs = new int[] {10,20,30,40,50};
        // int[] ys = new int[] {10,20,30,40,50};
        // String[] queries = new String[] {"p1","p2","p3","p4","p5"};
        System.out.println(Arrays.toString(findNearestCities(names, xs, ys, queries)));
    }

    public static String[] findNearestCities(String[] names, int[] xs, int[] ys, String[] queries) {
        Map<String, City> cities = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            cities.put(names[i], new City(names[i], xs[i], ys[i]));
        }

        Map<Integer, TreeSet<City>> xTree = new HashMap<>();
        Map<Integer, TreeSet<City>> yTree = new HashMap<>();

        for (City city : cities.values()) {
            xTree.putIfAbsent(city.x, new TreeSet<>(Comparator.comparingInt(a -> a.y)));
            yTree.putIfAbsent(city.y, new TreeSet<>(Comparator.comparingInt(a -> a.x)));

            xTree.get(city.x).add(city);
            yTree.get(city.y).add(city);
        }

        String[] results = new String[queries.length];

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            String result = "NONE";

            if (cities.containsKey(query)) {
                City city = cities.get(query);

                City xCityLower = xTree.get(city.x).lower(city);
                City xCityHigher = xTree.get(city.x).higher(city);

                City yCityLower = yTree.get(city.y).lower(city);
                City yCityHigher = yTree.get(city.y).higher(city);

                City resultCity = getNearestCityName(city, xCityLower, xCityHigher, yCityLower, yCityHigher);
                if (resultCity != null) {
                    result = resultCity.name;
                }
            }
            results[i] = result;
        }
        return results;
    }

    private static City getNearestCityName(City city, City xCityLower, City xCityHigher, City yCityLower, City yCityHigher) {
        City xCity = compareCities(city, xCityLower, xCityHigher);
        City yCity = compareCities(city, yCityLower, yCityHigher);

        return compareCities(city, xCity, yCity);
    }

    public static City compareCities(City city, City lower, City higher) {
        if (lower == null && higher == null)
            return null;

        if (lower == null)
            return higher;

        if (higher == null)
            return lower;

        if (city.getDistanceFrom(lower) < city.getDistanceFrom(higher)) {
            return lower;
        } else if (city.getDistanceFrom(lower) > city.getDistanceFrom(higher)) {
            return higher;
        } else {
            return lower.name.compareTo(higher.name) < 1 ? lower : higher;
        }
    }
}

class City {
    String name;
    int x;
    int y;

    City(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getDistanceFrom(City city) {
        return Math.abs(this.x - city.x) + Math.abs(this.y - city.y);
    }
}
