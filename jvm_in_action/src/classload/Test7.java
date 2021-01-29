package classload;

public class Test7 {
    private String a = "s1";

    {
        b=20;
    }
    private int b = 10;

    {
        a="s2";
    }
    public Test7(String a,int b){
        this.a = a;
        this.b=b;
    }

    public static void main(String[] args) {
        Test7 test7 = new Test7("s3",30);
        System.out.println(test7.a);
        System.out.println(test7.b);
    }
}
