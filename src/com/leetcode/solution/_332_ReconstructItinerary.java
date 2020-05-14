package com.leetcode.solution;

import java.util.*;

class Solution {
    private Map<String, PriorityQueue<String>> targets = new HashMap<>();
    private List<String> route = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets)
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        visit("JFK");
        return route;
    }

    private void visit(String airport) {
        while (targets.containsKey(airport) && !targets.get(airport).isEmpty())
            visit(targets.get(airport).poll());
        route.add(0, airport);
    }
}
