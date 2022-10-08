package ex5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class FunctionPractice {
    public static void main(String[] args) {
        System.out.println("请输入一个整数");
        //获取输入
        Scanner scanner =new Scanner(System.in);
        int number=scanner.nextInt();
        //调用方法一
        System.out.println("this is countPrimes1");
        countPrimes1(number);
        //调用方法二
        System.out.println("this is countPrimes2");
        countPrimes2(number);
        //调用方法三
        System.out.println("this is countPrimes3");
        countPrimes3(number);
        //分解质因数
        System.out.println("分解质因数");
        PrimeFactorization(number);
    }
    //分解质因数
    public static void PrimeFactorization(int number){
        //先找到小于number的所有质数，调用方法三
        List<Integer> minPrime=countPrimes3(number);
        List<Integer> result=new ArrayList<>();
        int temp=number;
        while(temp>2){
            for(int num:minPrime){
                if(temp%num==0){
                    result.add(num);
                    temp=temp/num;
                    break;
                }
            }
        }
        System.out.print(number+"=");
        for(int i=0;i<result.size()-1;i++){
            int num=result.get(i);
            System.out.print(num+"*");
        }
        System.out.println(result.get(result.size()-1));
    }
    //方法一找质数
    public static void countPrimes1(int number){

        List<Integer> result =new ArrayList<>();
        if(number<=2){
            System.out.println("not a primie");
        }
        //找质数，穷举法
        else{
            boolean isPrimie;
            for(int i=2;i<number+1;i++){
                isPrimie=true;
                for(int j=2;j<i;j++){
                    if(i%j==0){
                        isPrimie=false;
                        break;
                    }
                }
                if(isPrimie) result.add(i);
            }
            System.out.println(number+"之内共有"+result.size()+"个质数");
            for(int num:result){
                System.out.println(num);
            }
            System.out.println(" ");
        }
    }
    //方法二找质数
    public static void countPrimes2(int number){

        List<Integer> result =new ArrayList<>();
        if(number<=2){
            System.out.println("not a primie");
        }
        //找质数，利用定理，因为如果一个数是合数，那么它的最小质因数肯定小于等于它的平方根
        else{
            boolean isPrimie;
            for(int i=2;i<number+1;i++){
                isPrimie=true;
                for(int j=2;j<(int)Math.sqrt(i)+1;j++){
                    if(i%j==0){
                        isPrimie=false;
                        break;
                    }
                }
                if(isPrimie) result.add(i);
            }
            System.out.println(number+"之内共有"+result.size()+"个质数");
            for(int num:result){
                System.out.println(num);
            }
            System.out.println(" ");
        }

    }
    //方法三找质数
    public static List<Integer> countPrimes3(int number){

        List<Integer> result =new ArrayList<>();
        if(number<=2){
            System.out.println("not a primie");
        }
        //找质数，利用定理，如果一个数是合数，只要尝试小于根号x的质数即可,而这些质数，恰好前面已经算出来了
        else{
            boolean isPrimie;
            for(int i=2;i<number+1;i++){
                isPrimie=true;
                for(int num:result){
                    if(i%num==0){
                        isPrimie=false;
                        break;
                    }
                }
                if(isPrimie) result.add(i);
            }
            System.out.println(number+"之内共有"+result.size()+"个质数");
            for(int num:result){
                System.out.println(num);
            }
            System.out.println(" ");
        }
        return result;
    }
}
