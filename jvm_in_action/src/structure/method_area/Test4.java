package structure.method_area;

import java.util.ArrayList;
import java.util.List;

public class Test4 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i=0;i<300000;i++){
            String temp = "s"+i;
            list.add(temp.intern());
        }
    }
}
