package classload;

public class Test10 {
    public static void main(String[] args) {
        int i = 0;
        try {
            i=10;
        }catch (Exception e){
            i = 20;
        }finally {
            i=30;
        }
    }
}
