package ex2;

public class CalculateNum {
    public static void main(String[] args) {
        findMiltiple7();
        findOdd();
        narciNum();
    }
    //7的倍数
    public static void findMiltiple7(){
        int num=1;//初始值
        int sum=0;//总和
        int count=0;//个数
        while(num<=100){
            if(num%7==0){
                sum+=num;
                count++;
            }
            num++;
        }
        System.out.print("1到100中7的倍数的个数:"+count+"  ");
        System.out.println("总和:"+sum);
    }

    //找到奇数和
    public static void findOdd(){
        int num=1;//初始值
        int sum=0;//总和
        while(num<=100){
            if(num%2==1) sum+=num;
            num++;
        }
        System.out.println("1到100奇数和:"+sum);
    }
    //水仙花数
    public static void narciNum(){
        int num = 100;
        do {
            int unit = 0;//个位
            int decade = 0;//十位
            int hundred = 0;//百位
            unit = num % 10;
            decade = num / 10 % 10;
            hundred = num / 100;
            if (unit * unit * unit + decade* decade * decade + hundred * hundred * hundred == num) {
                System.out.println("水仙花数:"+num);
            }
            num++;
        } while (num < 1000);
    }
}
