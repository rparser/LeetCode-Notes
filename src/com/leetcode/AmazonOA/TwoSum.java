package com.leetcode.AmazonOA;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.*;

public class TwoSum {
    public List<Integer> IDsOfPackages(int truckSpace, List<Integer> packageSpace) {
        Map<Integer, Integer> spaceMap = new LinkedHashMap<>();
        int maxPackage = -1;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < packageSpace.size(); i++) {
            int complement = truckSpace - 30 - packageSpace.get(i);
//            System.out.println(complement+"test"+spaceMap.containsKey(complement));
//            System.out.println(spaceMap);
            if (spaceMap.containsKey(complement) && Math.max(complement, packageSpace.get(i)) > maxPackage) {
//                System.out.println(complement);
                maxPackage = Math.max(maxPackage, Math.max(complement, packageSpace.get(i)));
                result.clear();
                result.add(spaceMap.get(complement));
                result.add(i);
            }
            spaceMap.put(packageSpace.get(i), i);
        }
        return result;

    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.AmazonOA.TwoSum");
    }

    @Test
    public void testSolution() {
        int truckSpace = 90;
        List<Integer> packageSpace = Arrays.asList(1, 40, 20, 10, 25, 35, 60);
        Assert.assertEquals(Arrays.asList(1,2), IDsOfPackages(truckSpace, packageSpace));
    }
}