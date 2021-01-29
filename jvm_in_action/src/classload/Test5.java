package classload;

public class Test5 {
    public static void main(String[] args) {
        int i = 0;
        int x = 0;
        while (i<10){
            x=x++; //先进行load 再 ++，赋值应该在++之前
            i++;
        }
        System.out.println(x);
    }
}
