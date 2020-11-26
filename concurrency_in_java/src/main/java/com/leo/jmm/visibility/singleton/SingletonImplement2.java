package com.leo.jmm.visibility.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SingletonImplement2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        SingletonEnum.INSTANCE2.doSomething();
        Class clazz = Class.forName("com.leo.jmm.visibility.singleton.TestEnum");
//        System.out.println(clazz.getConstructor().newInstance(10, "1919"));
        Constructor cons = clazz.getDeclaredConstructor();
        cons.setAccessible(true);
        cons.newInstance(null);
    }
}
interface SingletonEnumInter{
    public List a = new ArrayList<>();
    public void doSomething();
}
//通过枚举,枚举类自动实现了Serializable
enum SingletonEnum implements SingletonEnumInter{
    INSTANCE{
        @Override
        public void doSomething() {
            System.out.println(a.size());
            System.out.println("ins1");
        }
    },
    INSTANCE2{
        @Override
        public void doSomething() {
            System.out.println(a.size());
            System.out.println("ins2");
        }
    };

}

