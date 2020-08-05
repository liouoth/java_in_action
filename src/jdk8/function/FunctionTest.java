package jdk8.function;

import java.util.function.Function;

/**
 * {@link java.util.function.Function}
 * Function是一个接口用来表示一个函数
 * 接口中定义了 抽象方法 apply,可以使用apply调用方法
 * 默认方法：
 *  - compose 先对传入的参数使用compose传入Function进行处理，然后再使用调用compose的Function
 *              (V v) -> apply(before.apply(v))
 *  - andThen 两个Function组合先后使用
 *  - identity
 */
public class FunctionTest {
    private static void funTest(){
        Function<Integer,Integer> function = T->T*2;
        System.out.println("输入1");
        System.out.println("输出"+function.apply(1));
    }

    private static void composeTest(){
        Function<Integer,Integer> function1= T->T*2;
        Function<Integer,Integer> function2 = T->T-2;
        System.out.println("输入1");
        System.out.println("输出"+function1.compose(function2).apply(1));
    }
    private static void andThenTest(){
        Function<Integer,Integer> function1= T->T*2;
        Function<Integer,Integer> function2 = T->T-2;
        System.out.println("输入1");
        System.out.println("输出"+function1.andThen(function2).apply(1));
    }

    public static void main(String[] args) {
//        funTest();
//        composeTest();
        andThenTest();
    }
}
