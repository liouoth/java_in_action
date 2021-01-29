package structure.direct_memory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        List<ByteBuffer> list = new ArrayList<>();
        int i = 0;
        try{
            while (true){
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100*1024*1024);
                list.add(byteBuffer);
                i++;
            }
        }finally {
            System.out.println(i);
        }
    }
}
