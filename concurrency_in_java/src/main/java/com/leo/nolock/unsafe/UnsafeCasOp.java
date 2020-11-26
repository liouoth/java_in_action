package com.leo.nolock.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class UnsafeCasOp {
    public static void main(String[] args) throws NoSuchFieldException {
        Student student = new Student("will",11);
        //自己封装的工具类
        Unsafe unsafe = GetUnSafe.getUnsafe();
        //因为是对象所以需要获取对象中的字段的偏移量
        //什么是偏移量？
        //objectFieldOffset 获取对象字段的偏移量，需要通过反射类来获取
        //因为是private，所以只用getDeclaredField
        Field nameField =  Student.class.getDeclaredField("name");
        Field ageField =  Student.class.getDeclaredField("age");
        //获取成员偏移量
        long ageOffset = unsafe.objectFieldOffset(ageField);
        long nameOffset = unsafe.objectFieldOffset(nameField);
        //使用cas操作替换对象中的成员变量值
        System.out.println(student);
        unsafe.compareAndSwapInt(student,ageOffset,11,22);
        System.out.println(student);
    }
}
class Student{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
