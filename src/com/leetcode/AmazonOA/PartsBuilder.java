package com.leetcode.AmazonOA;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class PartsBuilder {
    public int buildParts(int numberOfParts, List<Integer> boxList) {
        if(numberOfParts<=0) return 0;
        if(numberOfParts<=1) return boxList.get(0);
        int sum=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<numberOfParts;i++){
            pq.offer(boxList.get(i));
        }
        while(pq.size()>2){
            System.out.println(pq+" sum = "+sum);
//            System.out.println(pq.peek()+"first");
            int min=pq.poll();
            int secMin=pq.poll();
            sum+=(min+secMin);
            pq.offer(min+secMin);
//            System.out.println(pq.peek()+"second");
        }
        System.out.println(pq+" sum = "+sum);
        return sum+=pq.poll()+pq.poll();
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.AmazonOA.PartsBuilder");
    }

    @Test
    public void testSolution() {
        int numberOfParts = 4;

        List<Integer> boxList = Arrays.asList(8,4,6,12);
        Assert.assertEquals(58, buildParts(numberOfParts, boxList));
    }
}
