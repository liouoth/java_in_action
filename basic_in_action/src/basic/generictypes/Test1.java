package basic.generictypes;

public class Test1 {
    public static void main(String[] args) {
        Human human = new Human();
        Human.Man man = new Human.Man();
        human.say(man);
    }
}
class Human{
    static class Man extends Human{}
    static class Woman extends Human{}

    public void say(Human h){
        System.out.println("i am human");
    }
    public void say(Man m){
        System.out.println("i am man");
    }
    public void say(Woman w){
        System.out.println("i am woman");
    }
}
