package com.leetcode.oldCompanies.AmazonOA;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LogOrder {
    public List<String> reorderLogFiles(int numberOfBoixes, List<String> boxList) {
        Collections.sort(boxList, (box1, box2) -> {
            String[] split1 = box1.split(" ", 2);   // String.split(regex, limit) limit是把String分割为几段
            String[] split2 = box2.split(" ", 2);   // 分割为两段
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));  //看第二段（正文段）是字母还是数字，用isDigit
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {   // 如果都是字母
                int cmp = split1[1].compareTo(split2[1]);   // 按照字母排序第二段（正文）
                if (cmp != 0) return cmp;   // 如果有正负则比较字典序lexicographical(lexico-graphical)
                return split1[0].compareTo(split2[0]); //如果第二段一样，则比较第一段identifier
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;  // 如果不都是字母（有数字）则：
            // 即如果d1是数字，则看d2是不是数字，d2如果是数字，则d1在d2前（顺序不变，0）
            // 如果d2不是数字，则d2在d1前（返回1，即d1>d2，所以升序排列d2靠前d1靠后）
            // 如果d1不是数字，则d2必是数字，此时返回-1，即d1<d2，d1靠前d2靠后
        });
        return boxList;
    }


    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.oldCompanies.AmazonOA.LogOrder");
    }

    @Test
    public void testSolution() {
        int numberOfBOxes = 6;

        List<String> boxList = Arrays.asList("ykc 82 01", "eo first qpx", "09z cat hamster", "06f 12 25 6", "az0 first qpx", "236 cat dog rabbit snake");
        Assert.assertEquals(7, reorderLogFiles(numberOfBOxes, boxList));
    }
}

