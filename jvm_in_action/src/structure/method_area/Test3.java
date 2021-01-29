package structure.method_area;

public class Test3 {
    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "a"+"b";
        String s4 = s1+s2;
        String s5 = "ab";
        String s6 = s4.intern();
//        String s3 = "a"+"b"; 看一下intern时，会不会将堆中的对象引用放入常量池。答案是会
        //问
        System.out.println(s3==s4);
        System.out.println(s3==s5);
        System.out.println(s3==s6);
//        System.out.println(s4==s6);

        String x2 = new String("c1")+new String("d");
        String x1 = "cd";
        x2.intern();
        //如果调换最后两行代码的位置呢，如果是jdk1.6呢
        System.out.println(x1==x2);
    }
}
