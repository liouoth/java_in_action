package com.leo.concurrent_tool.collections.concurrenthashmap;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WordCountingDataImpl {
    public static void main(String[] args) {
//        demo(
//                ()-> new HashMap<String,Integer>(),
//                (map,list)->{
//                    list.forEach(
//                            l->{
//                                Integer i = map.get(l);
//                                map.put(l,i==null?1:i+1);
//                            }
//                    );
//                }
//        );
//        demo(
//                ()-> new ConcurrentHashMap<String,Integer>(),
//                (map,list)->{
//                    list.forEach(
//                            l->{
//                                synchronized (map){
//                                    Integer i = map.get(l);
//                                    map.put(l,i==null?1:i+1);
//                                }
//                            }
//                    );
//                }
//        );
//        demo(
//                () -> new ConcurrentHashMap<String, AtomicInteger>(),
//                (map, list) -> {
//                    list.forEach(
//                            l -> {
//                                AtomicInteger i = map.get(l);
//                                if (i!=null){
//                                    i.compareAndSet(i.get(), i.incrementAndGet());
//                                }else {
//                                    map.put(l,new AtomicInteger(1));
//                                }
//                            }
//                    );
//                }
//        );
//                demo(
//                () -> new ConcurrentHashMap<String, LongAdder>(),
//                (map, list) -> {
//                    list.forEach(
//                            //查看，put 都是一个操作那么就是原子的
//                            l->{
//                                //原子操作
//                                LongAdder adder = map.computeIfAbsent(l,
//                                        (key)->new LongAdder());
//                                //原子
//                                adder.increment();
//                            }
//                    );
//                }
//        );
        demo(
                () -> new ConcurrentHashMap<String, Integer>(),
                (map, list) -> {
                    list.forEach(
                            l -> {
                                map.merge(l,1,Integer::sum);
                            }
                    );
                }
        );
    }

    private static <V> void demo(Supplier<Map<String, V>> supplier,
                                 BiConsumer<Map<String, V>, List<String>> consumer) {
        Map<String, V> counterMap = supplier.get();
        List<Thread> ts = new ArrayList<>();
        for (int i = 1; i <= 26; i++) {
            int idx = i;
            Thread thread = new Thread(() -> {
                List<String> words = readFromFile(idx);
                consumer.accept(counterMap, words);
            });
            ts.add(thread);
        }
        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counterMap);
    }

    public static List<String> readFromFile(int i) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream("E:\\tutorial\\" + i + ".txt")))) {
            while (true) {
                String word = in.readLine();
                if (word == null) {
                    break;
                }
                words.add(word);
            }
            return words;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
