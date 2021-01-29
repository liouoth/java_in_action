package classload;

public class Test8 {
    public Test8(){}

    private void test1(){}

    private final void test2(){}

    public void test3(){}

    public static void test4(){}

    public static void main(String[] args) {
        Test8 t = new Test8();
        t.test1();
        t.test2();
        t.test3();
        t.test4();
        Test8.test4();
    }
}
