package jdk8.function.myFunc;

/**
 * 接口是不允许private的
 */
@FunctionalInterface
public interface DefMyFunc<T> {
    void printSelf(T t);
}
