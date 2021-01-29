package jdk8.lambda;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaTest1 {
    public static void main(String[] args) {
        /**
         * 从数组中挑选出一个最小的值
         */
        int [] arrays = {1,3,4,2};

        //命令式编程
        int min = Integer.MIN_VALUE;
        for (int i=0;i<arrays.length;i++){
            if (arrays[i]<min){
                min = arrays[i];
            }
        }
        System.out.println("命令式编程："+min);

        //函数式编程
        int funInt = IntStream.of(arrays).min().getAsInt();
        System.out.println("函数式编程："+funInt);

        //通过添加多线程函数，实现了多线程的拆分，比如计算1亿数字求最小值
//        int funInt = IntStream.of(arrays).parallel().min().getAsInt();





    }
}
