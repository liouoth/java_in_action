package com.leo.concurrent_tool.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //遗留的安全集合
        Hashtable hashtable = new Hashtable();
        hashtable.put(null,"a");//key需要调用hashcode方法，value则在方法中进行了判断
        //Collections装饰的线程安全集合
        Collections.synchronizedList(new ArrayList<>());
    }
}
