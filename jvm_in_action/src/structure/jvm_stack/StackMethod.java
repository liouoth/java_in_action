package structure.jvm_stack;

public class StackMethod {
    public static void main(String[] args) {
        test1();
        System.out.println("test");
    }

    private static void test1() {
        test2();
        System.out.println("test1");
    }

    private static void test2() {
        System.out.println("test2");
    }
}
