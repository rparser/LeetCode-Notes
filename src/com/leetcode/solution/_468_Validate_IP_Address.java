package com.leetcode.solution;

/**
 * O(N),O(N)
 */

class _468_Validate_IP_Address {
    public String validIPAddress(String IP) {
        // 基础判断，可以省略
        if (IP.startsWith(":") || IP.startsWith(".") || IP.endsWith(":") || IP.endsWith("."))
            return "Neither";

        //分割
        String[] arr = IP.split("\\.");
        // 如果是IPv4
        if (arr.length == 4) {
            for (int i = 0; i < 4; i++) {
                //如果长度为0 或 长度>3 或长度>1且由0开头 或有非数字 都是出错
                if (arr[i].length() == 0 || arr[i].length() > 3
                        || (arr[i].length() > 1 && arr[i].startsWith("0"))
                        || !arr[i].matches("[0-9]+"))
                    return "Neither";
                // 如果数字大于255也是出错
                int num = Integer.parseInt(arr[i]);
                if (num > 255)
                    return "Neither";
            }
            return "IPv4";
            //如果是IPv6
        } else {
            //以：分割
            arr = IP.split(":");
            if (arr.length == 8) {
                for (int i = 0; i < 8; i++)
                    //如果长度为0 或长度>4 或长度不符合数字+a~f
                    if (arr[i].length() == 0
                            || arr[i].length() > 4
                            || !arr[i].matches("([0-9]|[a-f]|[A-F])+"))
                        return "Neither";
                // 都符合返回v6
                return "IPv6";
            }
            return "Neither";
        }
    }
}
