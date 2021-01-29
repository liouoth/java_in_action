package classload;

public class Test2 {
    public static void main(String[] args) {
        int a = 10;
        int b = a++ + ++a +a--;
        System.out.println(b);
    }
}
