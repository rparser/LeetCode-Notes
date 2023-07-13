package com.leetcode.oldCompanies.Wepay;

import java.text.DecimalFormat;
import java.util.*;

public class Alerter {
    public Boolean alerter(List<Integer> inputs,
                           int windowSize,
                           float allowedIncrease) {

        // First check some special cases
        int len = inputs.size();
        if (len == 0) return false;

        // we first scan the nums, and get the maximum sliding window,
        // but which stores inputsIndex, because there may be duplicate elements
        int[] maxWindow = new int[len - windowSize + 1];
        int inputsIndex = 0;
        int windowIndex = 0;
        Deque<Integer> deque = new LinkedList<>();
        while (inputsIndex < len) {
            while (!deque.isEmpty() && deque.peek() < inputsIndex - windowSize + 1) deque.poll();
            while (!deque.isEmpty() && inputs.get(deque.peekLast()) < inputs.get(inputsIndex)) deque.pollLast();
            deque.addLast(inputsIndex);
            if (inputsIndex >= windowSize - 1) maxWindow[windowIndex++] = deque.peek();
            inputsIndex++;
        }

        // Then we scan the inputs twice, to get the average sliding window.
        float[] aveSlidingWindow = new float[len - windowSize + 1];
        inputsIndex = 0;
        windowIndex = 0;
        deque.clear();
        int sum = 0;
        while (inputsIndex < len) {
            while (!deque.isEmpty() && deque.peek() < inputsIndex - windowSize + 1) sum -= inputs.get(deque.poll());

            deque.addLast(inputsIndex);
            sum += inputs.get(inputsIndex);
            if (inputsIndex >= windowSize - 1) {
                float ave = (float) sum / (float) windowSize;
                DecimalFormat newFormat = new DecimalFormat("#.##");
                ave = Float.valueOf(newFormat.format(ave));
                aveSlidingWindow[windowIndex++] = ave;
            }
            inputsIndex++;
        }

        // Now we get the maximum sliding window which stores the inputsIndex of the maximum number in each window
        // And the average sliding window

        // Create a hashmap, for each individual maximum inputsIndex, map it to the average in all windows it appears
        HashMap<Integer, List<Float>> map = new HashMap<>();
        inputsIndex = 0;
        for (; inputsIndex < len - windowSize + 1; inputsIndex++) {
            int maxInputsIndex = maxWindow[inputsIndex];
            float ave = aveSlidingWindow[inputsIndex];

            if (!map.containsKey(maxInputsIndex)) {
                map.put(maxInputsIndex, new ArrayList<>());
                map.get(maxInputsIndex).add(ave);
            } else
                map.get(maxInputsIndex).add(ave);
        }

        // We check if the first condition meets
        for (Integer maxInputsIndex : map.keySet()) {
            List<Float> aves = map.get(maxInputsIndex);
            int max = inputs.get(maxInputsIndex);
            boolean shouldAlert = true;
            for (windowIndex = 0; windowIndex < aves.size(); windowIndex++) {
                float limit = aves.get(windowIndex) * allowedIncrease;
                if (max <= limit) {
                    shouldAlert = false;
                    break;
                }
            }
            if (shouldAlert)
                return true;
        }

        // Then we check if the second condition meets
        float prevMinAve = aveSlidingWindow[0];
        for (windowIndex = 1; windowIndex < aveSlidingWindow.length; windowIndex++) {
            float limit = prevMinAve * allowedIncrease;
            if (aveSlidingWindow[windowIndex] > limit)
                return true;
            if (aveSlidingWindow[windowIndex] < prevMinAve)
                prevMinAve = aveSlidingWindow[windowIndex];
        }
        return false;
    }

}
