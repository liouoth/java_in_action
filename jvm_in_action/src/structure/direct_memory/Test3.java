package structure.direct_memory;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Test3 {
    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024*1024*1024);
        System.out.println("分配完毕....");
        System.in.read();
        System.out.println("开始释放....");
        byteBuffer = null;
        System.gc();
        System.in.read();
    }
}
