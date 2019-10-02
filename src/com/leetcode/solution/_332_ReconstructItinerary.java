package com.leetcode.solution;

import java.util.*;

public class _332_ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets)
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        visit("JFK");
        return route;
    }

    private Map<String, PriorityQueue<String>> targets = new HashMap<>();
    private List<String> route = new LinkedList<>();

    private void visit(String airport) {
        while (targets.containsKey(airport) && !targets.get(airport).isEmpty())
            visit(targets.get(airport).poll());
        route.add(0, airport);
    }
}
