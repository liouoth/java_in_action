package jdk8.lambda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaTest4 {
    public static void main(String[] args) {
        //Consumer 是一个消费者，没有数据返回
        Consumer consumer = s-> System.out.println(s);
        //可以使用函数引用的形式简化上述代码,省略参数以及参数传入
        /**
         * 静态方法，传入静态对象，或者直接使用类
         */
        Consumer consumer1 = System.out::println;
        Function<String,Integer> function = Integer::valueOf;//(s)->return Integer.valueOf(s);
        //构造函数
        Supplier<String> supplier = String::new;
        /**
         * 成员方法
         */
        DateTimeFormatter df = DateTimeFormatter.ofPattern("");
        Function<LocalDateTime,String> function1 = df::format;
    }
}
