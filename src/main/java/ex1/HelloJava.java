package ex1;
import java.io.*;
import java.util.Scanner;

public class HelloJava {
    public static void main(String[] args) {
        //获取键盘输入
        Scanner scanner = new Scanner(System.in);
        //获取姓名
        System.out.println("请输入你的姓名");
        String name=scanner.nextLine();
        //获取性别
        System.out.println("请输入你的性别");
        String sex= scanner.nextLine();
        //获取籍贯
        System.out.println("请输入你的籍贯");
        String hometown=scanner.nextLine();
        //获取住址
        System.out.println("请输入你的住址");
        String address =scanner.nextLine();
        //打印输出
        System.out.println("你的基本信息为：");
        System.out.println("姓名："+name);
        System.out.println("性别："+sex);
        System.out.println("籍贯："+hometown);
        System.out.println("住址："+address);



    }
}
