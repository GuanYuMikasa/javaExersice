package ex4;

import java.util.Scanner;

public class StringExercise {
    public static void main(String[] args) {
        System.out.println("判断回文字符串");
        String str1 = "abcdcba";
        String str2 = "abcddcba";
        String str3 = "abcdefg";
        palindrome(str1);
        palindrome(str2);
        palindrome(str3);
        System.out.println("找到某个字符");
        findCharLast();
        System.out.println("十六进制数转十进制数");
        parse16To10();
    }
    //十六进制数转十进制数
    public static void parse16To10(){
        System.out.println("请输入一个十六进制数");
        Scanner scanner=new Scanner(System.in);
        StringBuilder builder = new StringBuilder(scanner.nextLine());
        //将字符串反转方便操作
        String reverseNum=builder.reverse().toString();
        //字符变量，方便后续判断
        char ch;
        //十进制数变量方便操作
        int num_10=0;
        for(int i=0;i<reverseNum.length();i++){
            ch=reverseNum.charAt(i);
            if(Character.isDigit(ch)){
                int numIndex=Integer.parseInt(""+ch);
                num_10+=numIndex*(int)Math.pow(16,i);
            }
            else{
                int numIndex=10+(ch-'a');
                num_10+=numIndex*(int)Math.pow(16,i);
            }
        }
        System.out.println("十进制值为："+num_10);
    }
    //找到某个字符
    public static void findCharLast(){
        System.out.println("请输入字符");
        //获取输入字符
        Scanner scanner=new Scanner(System.in);
        //使用character承接字符，方便使用equals
        Character ch= scanner.nextLine().charAt(0);
        System.out.println("请输入字符串");
        //获取字符串
        String str=scanner.nextLine();
        //定义位置变量
        int index = -1;
        //遍历查找
        for(int i=0;i<str.length();i++){
            if(ch.equals(str.charAt(i))){
                index=i;
            }
        }
        if(index==-1){
            System.out.println("Not Found");
        }
        else{
            System.out.println("index:"+index);
        }
    }
    //判断回文字符串
    public static void palindrome(String str){
        //临时变量
        String temp;
        //构建stringbuilder
        StringBuilder builder=new StringBuilder(str);
        //实现字符串反转
        temp=builder.reverse().toString();
        System.out.println(str);
        if(str.equals(temp)){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }
}
