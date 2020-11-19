package com.leo.share_mode.GuardedSuspession.mail;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class MailBox {
    //多线程竞争，使用线程安全的map,用于存放
    private static Map<Integer,GuardedObject> map = new Hashtable<>();

    //id
    private static int id = 0;

    public static Set<Integer> getIds(){
        return map.keySet();
    }

    //生成信箱
    public static synchronized int generateBox(){
        map.put(++id,new GuardedObject(id));
        return id;
    }

    public static Object get(int id,long timeout){
        return map.get(id).get(timeout);
    }

    public static void put(int id,Object object){
        map.get(id).put(object);
    }
}
