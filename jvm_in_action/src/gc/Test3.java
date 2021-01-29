package gc;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test3 {
    private static int array_size = 4*1024*1024;

    public static void test1() throws IOException {
        List<WeakReference<byte[]>> list = new ArrayList<>();
        for (int i =0;i<5;i++){
            WeakReference<byte []> weakReference = new WeakReference<byte[]>(new byte[array_size]);
            list.add(weakReference);
            list.forEach(l-> System.out.print(l.get()+" "));
            System.out.println();
        }
        System.out.println(list.size());
        System.in.read();
        list.forEach(l-> System.out.println(l.get()));
    }

    public static void main(String[] args) throws IOException {
        test1();
    }
}
