package com.leetcode.solution;

public class _1491_Average_Salary_Excluding_the_Minimum_and_Maximum_Salary {
    public double average(int[] salary) {
        int sum = 0;
        int maxSalary = salary[0];
        int minSalary = salary[0];
        for (int s : salary) {
            maxSalary = Math.max(s, maxSalary);
            minSalary = Math.min(s, minSalary);
            sum += s;
        }
        return (double) (sum - maxSalary - minSalary) / (salary.length - 2);
    }
}
