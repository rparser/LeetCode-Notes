class Solution {
    public String addStrings(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        if (n1 > n2) return addStrings(num2, num1);
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = n1 - 1, j = n2 - 1; j >= 0; i--, j--) {
            int sum = 0;
            if (i < 0) sum = Character.getNumericValue(num2.charAt(j)) + carry;
            else sum = Character.getNumericValue(num1.charAt(i)) + Character.getNumericValue(num2.charAt(j)) + carry;
            if (sum >= 10) carry = 1;
            else carry = 0;
            sum = sum % 10;
            sb.append(sum);
        }
        if (carry == 1) sb.append(1);
        return sb.reverse().toString();
    }
}