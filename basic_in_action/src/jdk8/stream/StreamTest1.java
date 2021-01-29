package jdk8.stream;

import java.util.stream.IntStream;

public class StreamTest1 {
    public static void main(String[] args) {
        int [] arr = {1,2,3,4};
        int sum = 0;
//        for (int i=0;i<arr.length;i++){
//            sum+=arr[i];
//        }
//        System.out.println("内部sum："+sum);

        sum = IntStream.of(arr).map(x->x*2).sum();
        IntStream.of(arr).map(x->{
            System.out.println("double");return x*2;});
    }
}
