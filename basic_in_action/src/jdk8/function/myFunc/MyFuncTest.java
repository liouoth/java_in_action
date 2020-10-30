package jdk8.function.myFunc;

public class MyFuncTest {
    public static void main(String[] args) {
        DefMyFunc<Integer> defMyFunc = T-> System.out.println(T);
        defMyFunc.printSelf(1);
    }

}
