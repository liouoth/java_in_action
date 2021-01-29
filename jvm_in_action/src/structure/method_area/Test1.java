package structure.method_area;


import com.sun.org.apache.xpath.internal.compiler.OpCodes;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 元空间
 * -XX:MaxMetaspaceSize=8m
 */
public class Test1 extends ClassLoader{ //可以用来加载类的二进制字节码
    public static void main(String[] args) {
        //方法区是加载类文件
        int j = 0;
        try {
            Test1 test1 = new Test1();
            for (int i = 0;i<10000;i++,j++){
                ClassWriter classWriter = new ClassWriter(0);
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
                byte [] codes = classWriter.toByteArray();
                test1.defineClass("Class"+i,codes,0,codes.length);
            }
        }finally {
            System.out.println(j);
        }
    }
}
