class Solution {
    public String validIPAddress(String IP) {
        if (IP.startsWith(":") || IP.startsWith(".") || IP.endsWith(":") || IP.endsWith(".")) {
            return "Neither";
        }
        //以.分割，判断是否为 IPv4
        String[] arr = IP.split("\\.");
        if (arr.length == 4) {
            for (int i = 0; i < 4; i++) {
                if (arr[i].length() == 0 || arr[i].length() > 3 || (arr[i].length() > 1 && arr[i].startsWith("0")) || !arr[i].matches("[0-9]+")) {
                    return "Neither";
                }
                int num = Integer.parseInt(arr[i]);
                if (num > 255) {
                    return "Neither";
                }
            }
            return "IPv4";
        } else {
            //以：分割 判断是否为 IPv6
            arr = IP.split(":");
            if (arr.length == 8) {
                for (int i = 0; i < 8; i++) {
                    if (arr[i].length() == 0 || arr[i].length() > 4 || !arr[i].matches("([0-9]|[a-f]|[A-F])+")) {
                        return "Neither";
                    }
                }
                return "IPv6";
            }
        }
        return "Neither";
    }

}
