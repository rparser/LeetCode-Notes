public class Pokes {

    private String[] sign={"方片","红桃","黑桃","梅花"};
    private static String[] hands = new String[4];
    private static ArrayList<String> List;

    Pokes(){
    }

    public void Init(){
        List = new ArrayList();
        for(String str:sign){
            for(int i = 1;i<=13;i++){
                String extra_sign = ""; //将数字与扑克匹配
                if(i == 1){
                    extra_sign = "A";
                    List.add(str+extra_sign);
                }else if(i == 11){
                    extra_sign = "J";
                    List.add(str+extra_sign);
                }else if(i == 12){
                    extra_sign = "Q";
                    List.add(str+extra_sign);
                }else if(i == 13){
                    extra_sign = "K";
                    List.add(str+extra_sign);
                }else{
                    List.add(str+i);
                }
            }
        }
    }

    public void Hands(){
        int i = 0,j=0;
        Collections.shuffle(List);//Collection类中的shuffle方法将＜List＞乱序排列
        for(String str:List){
            if(j == 4)
                break;
            if(i == 0){
                hands[j] = str+" ";
                i++;
            }
            //这里不使用for(i<13){
            //    hands[j] = str+" ";
            //    i++;
            //}是因为第一个下标为0的元素为null，所以多了一步判断
            if(0<i && i<13){
                hands[j] +=str+" " ;
                i++;
            }else{
                i = 0;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        Pokes p = new Pokes();
        p.Init();
        p.Hands();
        System.out.println(hands[0]);
        System.out.println(hands[1]);
        System.out.println(hands[2]);
        System.out.println(hands[3]);

    }

}