//三个循环体：
//        1.计算目标数组每个字母的个数
//        2.根据reference开始循环输出
//        3.最后添加reference中没有出现的字母

class Solution {
    public String customSortString(String S, String T) {
        StringBuilder sb=new StringBuilder();
        int []cnt=new int[26];
        for (char i:T.toCharArray())
            cnt[i-'a']++;

        for (int i=0;i<S.length();i++){
            for (int j=0;j<cnt[S.charAt(i)-'a'];j++){
                sb.append(S.charAt(i));
            }
            cnt[S.charAt(i)-'a']=0;
        }
        for (int i=0;i<26;i++){
            if (cnt[i]!=0){
                for (int j=0;j<cnt[i];j++){
                    sb.append((char)(i+'a'));
                }
            }
        }
        return sb.toString();
    }
}
