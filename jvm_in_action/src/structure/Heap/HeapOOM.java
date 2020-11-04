package structure.Heap;

import java.util.ArrayList;
import java.util.List;

//out of memory
public class HeapOOM {
    public static void main(String[] args) {
        int i = 0;
        String a = "hello";
        List<String> list = new ArrayList<>();
        try {
            while (true){
                a = a+a;
                list.add(a);
                i++;
            }
        } catch (Throwable e) {
            System.out.println("次数:"+i);
            e.printStackTrace();
        }
    }
}
