package jdk8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest4 {
    public static void main(String[] args) {
        String str = "my name is 008";

        //order都是和并行流相关的
        //foreach ：乱序，如果是forEachOrdered，则顺序正确
//        str.chars().parallel().forEachOrdered(System.out::println);
//        str.chars().parallel().forEach(s->System.out.println((char)s));
        str.chars().parallel().forEachOrdered(s->System.out.println((char)s));

        //collect 收集器
        List<String> collect = Stream.of(str.split(" ")).collect(Collectors.toList());

        //reduce 减少,返回成一个数据
        Optional<String> reduce1 = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
        //加一个默认的参数
        String reduce2 = Stream.of(str.split(" ")).reduce("",(s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce1.orElse(""));
        //求单词总长度
        Optional<Integer> optionalInteger = Stream.of(str.split(" ")).map(s -> s.length()).reduce((l1, l2) -> l1 + l2);
        System.out.println(optionalInteger.orElse(0));

        //max
        Optional<String> max = Stream.of(str.split(" ")).max(Comparator.comparingInt(String::length));
        System.out.println("最长的："+max.orElse(""));

        //短路操作，一般针对于无限流
        OptionalInt first = new Random().ints().findFirst();
        System.out.println("first:"+first);
    }
}
