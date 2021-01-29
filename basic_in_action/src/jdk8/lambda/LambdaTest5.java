package jdk8.lambda;

public class LambdaTest5 {
    public static void main(String[] args) {
        A a = new A();
        a.aFunc(1);
        a.aFunc();
    }
}
class A{
    public void aFunc(A this){};
    public void aFunc(A this,int b){}
}