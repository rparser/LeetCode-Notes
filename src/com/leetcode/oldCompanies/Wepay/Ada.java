package com.leetcode.oldCompanies.Wepay;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Ada {
    public int AdaLovelace(int year) {
        LocalDate ada = LocalDate.of(year, 10, 1);
        if (ada.getDayOfWeek().equals(DayOfWeek.TUESDAY))
            return ada.plusWeeks(1).getDayOfMonth();
        else
            return ada.with(TemporalAdjusters.next(DayOfWeek.TUESDAY)).plusWeeks(1).getDayOfMonth();
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.oldCompanies.Wepay.Ada");
    }

    @Test
    public void testSolution() {
        Assert.assertEquals(9, AdaLovelace(2018));
    }
}

