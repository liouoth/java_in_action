package jdk8.stream;

import java.util.Random;
import java.util.stream.Stream;

public class StreamTest3 {
    public static void main(String[] args) {
        //map
        String str = "my name is 008";
        Stream.of(str.split(" ")).map(
                s->s.length()
        ).forEach(System.out::println);
        //flatmap,注意IntStream以及LongStream不是Stream的子类
        Stream.of(str.split(" ")).flatMap(
                s->s.chars().boxed()
        ).forEach(s->{
            System.out.println((char)s.intValue());
        });
        //peek中间操作
        Stream.of(str.split(" ")).peek(
               System.out::println
        ).forEach(System.out::println);

        //limit filter
        System.out.println("=======limit filter========");
        new Random().ints().filter(i->i>10&&i<1000).limit(10).forEach(System.out::println);
    }
}
