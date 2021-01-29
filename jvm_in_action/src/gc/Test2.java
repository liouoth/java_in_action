package gc;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 设置堆内存 -XMX=10m
 */
public class Test2 {
    private static int array_size = 4*1024*1024;
    public static void main(String[] args) throws IOException {
        test3();
//        test2();
//        test1();
    }

    public static void test1() throws IOException {
        List<byte []> list = new ArrayList<>();
        for (int i =0;i<5;i++){
            list.add(new byte[array_size]); //4m 肯定会超出堆的大小
        }
        System.in.read();
    }

    public static void test2() throws IOException {
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i =0;i<5;i++){
            SoftReference<byte []> softReference = new SoftReference<byte[]>(new byte[array_size]);
            System.out.println(softReference);
            list.add(softReference);
        }
        System.out.println(list.size());
        System.in.read();
        list.forEach(l-> System.out.println(l.get()));
    }
    //添加软引用队列
    public static void test3() throws IOException {
        List<SoftReference<byte[]>> list = new ArrayList<>();
        //软引用队列
        ReferenceQueue referenceQueue = new ReferenceQueue();
        for (int i =0;i<5;i++){
            //关连队列
            SoftReference<byte []> softReference = new SoftReference<byte[]>(new byte[array_size],referenceQueue);
            System.out.println(softReference);
            list.add(softReference);
        }
        System.out.println(list.size());
        System.in.read();
        Reference poll = referenceQueue.poll();
        while (poll!=null){
            list.remove(poll);
            poll = referenceQueue.poll();
        }
        System.out.println(list.size());
        list.forEach(l-> System.out.println(l.get()));
    }
}
