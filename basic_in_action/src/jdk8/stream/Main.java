package jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link java.util.stream.Stream}
 *     Stream 继承了 {@link java.util.stream.BaseStream} 中有关于流的规范，自动关闭等等
 *      Stream中定义了 map、filter、sort 等等方法
 * {@link java.util.stream.ReferencePipeline} : ReferencePipeline中实现了map、filter的源代码
 *       Details：
 * {@link java.util.stream.AbstractPipeline}
 *
 */
public class Main {
    public static void main(String[] args) {
        List<String>  list= new ArrayList<>();
        list.add("leo");
        System.out.println("1");
    }
}
