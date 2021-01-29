package gc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) throws IOException {
        //分析前后堆的变化
        List<Object> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        System.out.println(1);
        System.in.read();

        list = null;
        System.out.println(2);
        System.in.read();
        System.out.println("end....");
    }
}
