package jdk8.lang._enum;

import java.util.HashMap;
import java.util.Map;

public class Test1 {
    public static HashMap<Integer,PowerOfTwo> map = new HashMap();
    public static void main(String[] args) {
        PowerOfTwo i = PowerOfTwo.fromInt(2);
        System.out.println(i);
    }
}enum PowerOfTwo {
    ONE(1), TWO(2), FOUR(4), EIGHT(8);

    private int value;

    PowerOfTwo(int value) {
        this.value = value;
        registerValue(); //map.put(value, this);
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    private void registerValue() {
        Test1.map.put(value, this);
    }

    public static PowerOfTwo fromInt(int i) {
        return Test1.map.get(i);
    }

    private static final Map<Integer, PowerOfTwo> map = new HashMap<Integer, PowerOfTwo>();
}
