package structure.direct_memory;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;

public class Test4 {
    static long _1GB = 1024 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
        //只能通过反射，由于双亲委派的机制
        Unsafe unsafe = getUnsafe();
        //分配直接内存
        long base = unsafe.allocateMemory(_1GB);
        unsafe.setMemory(base, _1GB, (byte) 0);
        System.in.read(); //进行停止
        //释放内存
        unsafe.freeMemory(base);
        System.in.read();
    }

    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            return unsafe;
        } catch (NoSuchFieldException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }
}
