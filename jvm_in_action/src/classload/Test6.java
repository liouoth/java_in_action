package classload;

public class Test6 {
    static int i = 10;
    static {
        i=20;
    }
    static {
        i=30;
    }
}
