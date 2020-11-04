package structure.counter;

//编译之后使用javap 反编译成可以看懂的字节码文件
public class Counter {
    public static void main(String[] args) {
        int a = 0;
        int v = a;
        if (a==v){
            System.out.println("hahah");
        }
    }
}
