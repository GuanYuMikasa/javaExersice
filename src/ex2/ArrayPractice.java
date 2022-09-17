package ex2;

import java.util.Random;

public class ArrayPractice {
    public static void main(String[] args) {
        sortArray(20);
        triangle();
        matrix();
    }
    //产生10个2位正整数，按从小到大排序，输入任一正整数，插入到原有数据序列中，保持从小到大次序不变
    public static void sortArray(int num){
        Random rd = new Random();
        //定义数组
        int[] array = new int[10];

        for(int i=0;i<10;i++){
            //生成随机数
            int temp= rd.nextInt(90)+10;
            array[i]=temp;
        }
        //排序
        for(int i=0;i<array.length-1;i++){
            for(int j=0;j<array.length-1-i;j++){
                if(array[j]>array[j+1]){
                    int temp =array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                }
            }
        }
        System.out.println("插入前：");
        for(int i=0;i<array.length;i++) System.out.print(array[i]+" ");
        System.out.println(" ");
        //声明新数组
        int array2[]=new int[11];
        //数组复制
        for(int i=0;i<10;i++) array2[i]=array[i];
        //记录插入位置
        int position=0;
        for(int i=0;i<10;i++){
            if(array2[i]<=num && num<=array2[i+1]) position = i+1;
        }
        //后续数字依次挪位
        for(int i=array2.length-1; i>position;i--){
            array2[i]=array2[i-1];
        }
        //插入数字
        array2[position]=num;
        System.out.println("插入后：");
        for(int i=0;i<array2.length;i++) System.out.print(array2[i]+" ");
        System.out.println(" ");
    }
    public static void triangle(){
        //创建二维数组
        int[][] arr=new int[10][10];
        //依据杨辉三角特性赋值二维数组
        for (int i=0;i<10;i++)
            for (int j=0;j<=i;j++){
                if (j==0||i==j)
                    arr[i][j]=1;
                else {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
            }
        for(int i=0;i<10;i++){
            for(int j=0;j<i;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
    public static void matrix(){
        //题目中示例数组
        int matrixC[][]={{1,2,3},{4,5,6},{7,8,9}};
        int matrixD[][]={{2,2,2},{1,1,1},{3,3,3}};
        //相加结果
        int tempAdd[][]={{0,0,0},{0,0,0},{0,0,0}};
        //相乘结果
        int tempMulti[][]={{0,0,0},{0,0,0},{0,0,0}};
        //相加
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                tempAdd[i][j]=matrixC[i][j]+matrixD[i][j];
        //相乘
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++)
                    tempMulti[i][j]+=matrixC[i][k]*matrixD[k][j];
            }
        //拼接字符串
        String str1 = "";
        for (int i = 0;i < 3;i++)
        {
            for (int j = 0;j < 3;j++)
            {
                str1 += tempAdd[i][j];
                if (j < 3)
                {
                    str1 += " ";
                }
                if (j == 2)
                {
                    str1 += "\n";
                }
            }
        }
        String str2 = "";
        for (int i = 0;i < 3;i++)
        {
            for (int j = 0;j < 3;j++)
            {
                str2 += tempMulti[i][j];
                if (j < 3)
                {
                    str2 += " ";
                }
                if (j == 2)
                {
                    str2 += "\n";
                }
            }
        }
        System.out.println(str1);
        System.out.println(str2);
    }
}
