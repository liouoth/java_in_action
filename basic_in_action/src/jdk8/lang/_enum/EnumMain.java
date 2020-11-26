package jdk8.lang._enum;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *  @name EnumMain
 *  @Description 描述Enum的学习
 *  @author will7 Mao
 *  @Date  2020/11/23
 */
public class EnumMain {
    private String name;
    private EnumMain(){}
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = SexEnum.class.getDeclaredConstructor(String.class,Integer.TYPE,String.class);
        constructor.setAccessible(true);
        constructor.newInstance("男人");
    }
}
