package jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest2 {
    public static void main(String[] args) {
        //从集合中创建
        List list = new ArrayList<>();
        list.stream();
        //从数组创建
        IntStream.of(new int[]{1,2});
        //利用random实现一个无限流,并作限制
        new Random().ints().limit(10);

        //自己产生
        Random random = new Random();
        Stream.generate(()->random.nextInt(10)).limit(10);

    }
}
