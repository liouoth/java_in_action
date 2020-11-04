package structure.jvm_stack;

public class StackOverFlow {
    static int count = 0;
    public static void main(String[] args) {
        try {
            test();
        } catch (Throwable e) {
            System.out.println("次数:"+count);
            e.printStackTrace();
        }
    }

    private static void test() {
        count++;
        test();
    }
}
