package classload;

public class Test11 {
    public static void main(String[] args) {
        int result = test();
        System.out.println(result);
    }

    private static int test() {
        try{
            return 10;
        }finally {
            return 20;
        }
    }
}
