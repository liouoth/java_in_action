package jdk8.lambda;

import java.util.stream.IntStream;
@FunctionalInterface
interface Interface1{
    int doubleNum(int i);

    //默认方法,默认实现，比如List方法中的函数
    default int add(int i ){
        return i *2;
    }
}

interface Interface2{
    int doubleNum(int i);

    //默认方法,默认实现，比如List方法中的函数
    default int add(int i ){
        return i *2;
    }
}
interface Interface3 extends Interface1,Interface2{
    @Override
    default int add(int i) {
        return Interface1.super.add(i); //通过调用父接口的方法,记住这个用法
    }
    //提示错误，两个接口中都存在add方法，需要实现哪一个

}


public class LambdaTest2 {
    public static void main(String[] args) {
        //jdk8 lambda表达式
        Runnable runnable = ()->{};
        //返回了一个实现了口的对象实例,可以通过强转的方式
        Object run = (Runnable)()->{};

//        Interface1 interface1 = (i)->i*2;
        //多行，还可以使用return
        Interface1 interface1 = (int i)->{return i*2;};

        //函数式编程的接口方法只有一个 @FunctionInterface,这个注解用于编译，接口中只能有一个方法。单一责任制
        //接口可以有默认方法
    }
}
