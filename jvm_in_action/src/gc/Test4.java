package gc;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//-Xms20M -Xmx20m -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc
public class Test4 {
    private final static int _512k= 5*1024;
    private final static int _1m= 1024*1024;
    private final static int _6m= 6*1024*1024;
    private final static int _7m= 7*1024*1024;
    private final static int _8m= 8*1024*1024;

    public static void main(String[] args) throws InterruptedException {
//        ArrayList<byte [] > list = new ArrayList<>();
//        list.add(new byte[_8m]);
//        list.add(new byte[_8m]);
        //当分配内存在线程中
        new Thread(()->{
            ArrayList<byte [] > list = new ArrayList<>();
            list.add(new byte[_8m]);
            list.add(new byte[_8m]);
        }).start();
        System.out.println("睡眠");
        TimeUnit.SECONDS.sleep(3);
    }
}
