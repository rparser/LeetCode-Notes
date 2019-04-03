package com.leetcode.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class TextBoxList {
    class TextBox{
        int width;
        int height;
        int x;
        int y;
        int dashHeight; // dashHeight <= height

        public TextBox(int width, int height, int x, int y, int dashHeight) {
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
            this.dashHeight = dashHeight; //基准线举例上边距离
        }
    }

    public List<List<TextBox>> solution (int pageWidth, List<TextBox> textBoxList){
        int i=0;
        List<Integer> widthList = new ArrayList<>(); //下面线的坐标
        List<Integer> heightList = new ArrayList<>();
        widthList.add(0);
        heightList.add(0);
        while(i<textBoxList.size()){
            int j=i;
            int curColumnWidth=0;
            while(j<textBoxList.size()){
                curColumnWidth+=textBoxList.get(j).width;
                if(curColumnWidth>pageWidth){ //每一行可以容纳的box
                    j--;
                    break;
                }
            }
            int curMaxHeight=0;
            int curWidth=0;
            // j - 本行最后一个, i - 本行第一个
            while(i<=j){ ;
                i++;
            }

            i=j;
        }

        return null;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.common.TextBoxList");
    }

    @Test
    public void testSolution() {

    }
}
