package jdk8.lambda;

import java.util.function.Function;

public class LambdaTest6 {
    public static void main(String[] args) {
       Function<Integer,Function<Integer,Integer>> function =  x->y->x+y;
    }
}


