package jdk8.function;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * {@link java.util.function.Consumer}
 * definition ： 消费者函数，用于消费一个数据
 * is expected to operate via side-effects. 用户期待的是effects ，无意识的是side-effects
 * 像return的是effects
 *
 * function：
 * accept
 * andThen: 接收对象必须是Consumer的子类
 *
 * jdk中应用：
 * Iterable foreach 中的使用
 *     default void forEach(Consumer<? super T> action) {
 *         Objects.requireNonNull(action);
 *         for (T t : this) {
 *             action.accept(t);
 *         }
 *     }
 * Iterator中也存在forEachRemaining
 */

public class ConsumerTest {
    /**
     * 一个简单的平方计算
     */
    public static void squareConsumerTest() {
        Consumer<Integer> square = x-> System.out.println("print square :" + x*x);
//        Function<Integer,Integer> squaref = x->{return x*x};
//        squaref.andThen(square).apply(1);
        //不可行
    }

    public static void forEachConsumerTest(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.forEach(a->{
            System.out.println(a);
        });
    }

    public static void main(String[] args) {
        squareConsumerTest();
    }
}
