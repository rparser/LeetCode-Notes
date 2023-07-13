package com.leetcode.oldCompanies.PureStorage;

import java.util.HashMap;

public class Sandbox {
//    public List<Integer> IDsOfPackages(int truckSpace, List<Integer> packageSpace) {
//        Map<Integer, Integer> spaceMap = new LinkedHashMap<>();
//        int maxPackage = -1;
//        List<Integer> result = new ArrayList<>();
//        for (int i = 0; i < packageSpace.size(); i++) {
//            int complement = truckSpace - 30 - packageSpace.get(i);
////            System.out.println(complement+"test"+spaceMap.containsKey(complement));
////            System.out.println(spaceMap);
//            if (spaceMap.containsKey(complement) && Math.max(complement, packageSpace.get(i)) > maxPackage) {
////                System.out.println(complement);
//                maxPackage = Math.max(maxPackage, Math.max(complement, packageSpace.get(i)));
//                result.clear();
//                result.add(spaceMap.get(complement));
//                result.add(i);
//            }
//            spaceMap.put(packageSpace.get(i), i);
//        }
//        return result;
//
//    }

    public static void main(String[] args) {
        // Creating an empty HashMap
        HashMap<String, Integer> hash_map = new HashMap<String, Integer>();

        // Mapping int values to string keys
        hash_map.put("Geeks", 10);
        hash_map.put("4", 15);
        hash_map.put("Geeks", 20);
        hash_map.put("Welcomes", 25);
        hash_map.put("You", 30);

        // Displaying the HashMap
//        System.out.println("Initial Mappings are: " + hash_map);
//
//        // Checking for the key_element 'Welcomes'
//        System.out.println("Is the key 'Welcomes' present? " +
//                hash_map.containsKey("Welcomes"));
//
//        // Checking for the key_element 'World'
//        System.out.println("Is the key 'World' present? " +
//                hash_map.containsKey("Yo"+"u"));

        HashMap<int[], Integer> map2 = new HashMap<>();
        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{1, 2, 3};
        map2.put(a, 22);
        System.out.println("Is b present? " +
                hash_map.containsKey(b));
//        JUnitCore.main("com.leetcode.oldCompanies.AmazonOA.TwoSum");
    }

//    @Test
//    public void testSolution() {
//        int truckSpace = 90;
//        List<Integer> packageSpace = Arrays.asList(1, 40, 30,20, 10, 25, 35, 60);
//        Assert.assertEquals(Arrays.asList(1,3), IDsOfPackages(truckSpace, packageSpace));
//    }
}
