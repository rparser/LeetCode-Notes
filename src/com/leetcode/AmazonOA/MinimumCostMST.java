package com.leetcode.AmazonOA;

import org.junit.*;
import org.junit.runner.*;

import java.util.*;

//mst修路 input list roadsAvaliable 已经包含了之后的 list costnewroadconstruct里所有的路， 我当时没时间了，直接把costnewroadconstruct 里所有的路从roadsAvaliable里删除了，然后再做union find。要不永远都会返回0.
public class MinimumCostMST {

    int getMinimumCostToConstruct(int numTotalAvailableCities, int numTotalAvailableRoads, List<List<Integer>> roadsAvailable,
                                  int numNewRoadsConstruct, List<List<Integer>> costNewRoadsConstruct) {
//        if(numTotalAvailableRoads>=numTotalAvailableCities-1 || numTotalAvailableCities<2) return 0;
        if (numTotalAvailableCities < 2) return 0;
        UnionFindSet ufSet = new UnionFindSet(numTotalAvailableCities);
        int existingRoad = 0;
        for (List<Integer> road : roadsAvailable) {
            int city1 = road.get(0);
            int city2 = road.get(1);
//            System.out.println("test22   "+ufSet.Find(city1)+"    and   " +ufSet.Find(city2));
            if (ufSet.Find(city1) != ufSet.Find(city2)) {

                ufSet.Union(city1, city2);
//                System.out.println("test "+ufSet.Find(1)+"    and   " +ufSet.Find(2)+"    and   " +ufSet.Find(3)+"    and   " +ufSet.Find(4)+"    and   " +ufSet.Find(5)+"    and   " +ufSet.Find(6)+"    and   " );
                existingRoad++;
            }
        }
        PriorityQueue<NewRoadCost> pq = new PriorityQueue<>(numNewRoadsConstruct, Comparator.comparingInt(a -> a.cost));
        for (List<Integer> newRoad : costNewRoadsConstruct) {
            NewRoadCost newRoadCost = new NewRoadCost(newRoad.get(0), newRoad.get(1), newRoad.get(2));
            pq.offer(newRoadCost);
        }

        List<NewRoadCost> mst = new ArrayList<>();
        while (pq.size() > 0 && mst.size() + existingRoad < numTotalAvailableCities - 1) {
//            while(pq.size()>0 ){
//            System.out.println(mst.size() + "  mstsize   " + existingRoad + "    " + numNewRoadsConstruct);
            NewRoadCost newRoadTemp = pq.poll();
//            System.out.println(newRoadTemp.toString());
            int city1 = newRoadTemp.city1;
            int city2 = newRoadTemp.city2;
            if (ufSet.Find(city1) != ufSet.Find(city2)) {
//                System.out.println(ufSet.Find(1)+"    and   " +ufSet.Find(2)+"    and   " +ufSet.Find(3)+"    and   " +ufSet.Find(4)+"    and   " +ufSet.Find(5)+"    and   " +ufSet.Find(6)+"    and   " );
//                System.out.println(ufSet.Find(city1)+"    and   " +ufSet.Find(city2));
                ufSet.Union(city1, city2);
//                System.out.println(ufSet.Find(city1)+"    and   " +ufSet.Find(city2));
                mst.add(newRoadTemp);
            }
        }
        if (mst.size() + existingRoad < numTotalAvailableCities - 1) return -1;
        int sum = 0;
        for (NewRoadCost newRoad : mst) sum += newRoad.cost;
        return sum;
    }

    class NewRoadCost {
        int city1;
        int city2;
        int cost;

        public NewRoadCost(int city1, int city2, int cost) {
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "NewRoadCost{" +
                    "city1=" + city1 +
                    ", city2=" + city2 +
                    ", cost=" + cost +
                    '}';
        }
    }

    class UnionFindSet {
        private int[] parents_;
        private int[] ranks_;

        public UnionFindSet(int n) {
            parents_ = new int[n + 1];
            ranks_ = new int[n + 1];
            for (int i = 0; i < parents_.length; ++i) {
                parents_[i] = i;
                ranks_[i] = 1;
            }
        }

        public boolean Union(int u, int v) {
            int pu = Find(u);
            int pv = Find(v);
            if (pu == pv) return false;

            if (ranks_[pv] > ranks_[pu])
                parents_[pu] = pv;
            else if (ranks_[pu] > ranks_[pv])
                parents_[pv] = pu;
            else {
                parents_[pv] = pu;
                ranks_[pu] += 1;
            }

            return true;
        }

        public int Find(int u) {
            while (parents_[u] != u) {
                parents_[u] = parents_[parents_[u]];
                u = parents_[u];
            }
            return u;
        }
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.AmazonOA.MinimumCostMST");
    }

    @Test
    public void testSolution() {
        int numTotalAvailableCities = 6;
        int numTotalAvailableRoads = 3;
        List<List<Integer>> roadsAvailable = Arrays.asList(Arrays.asList(1, 4), Arrays.asList(4, 5), Arrays.asList(2, 3));
        int numNewRoadsConstruct = 4;
        List<List<Integer>> costNewRoadsConstruct = Arrays.asList(Arrays.asList(1, 2, 5), Arrays.asList(1, 3, 10), Arrays.asList(1, 6, 2), Arrays.asList(5, 6, 5));
        Assert.assertEquals(7, getMinimumCostToConstruct(numTotalAvailableCities, numTotalAvailableRoads, roadsAvailable, numNewRoadsConstruct, costNewRoadsConstruct));
    }
}
