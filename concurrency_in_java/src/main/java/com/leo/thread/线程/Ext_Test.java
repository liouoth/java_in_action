package com.leo.thread.线程;

import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 使用benchmark测试一下 indexof快还是 正则快
 */
@Fork
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 2)
@Measurement(iterations = 2)
public class Ext_Test {
    static String [] STRING_ARR = new String [1000000];
    static Random random = new Random();
    static String query;
    static Pattern match;
    static Pattern find;
    static String RANDOM_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,./;'[]-";
    static {
        for (int i =0;i<1000000;i++){
            STRING_ARR[i] = getRandomString();
        }
        query = RANDOM_STRING.charAt(random.nextInt(59))+""+RANDOM_STRING.charAt(random.nextInt(59));
        match = Pattern.compile(".+"+query+".+");
        find = Pattern.compile(query);
    }

    static String getRandomString(){
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<10;i++){
            sb.append(RANDOM_STRING.charAt(random.nextInt(59)));
        }
        return sb.toString();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void test_indexof(){
        for (int i =0;i<1000000;i++){
            STRING_ARR[i].indexOf(query);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void test_contains(){
        for (int i =0;i<1000000;i++){
            STRING_ARR[i].contains(query);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void test_regMatch(){
        for (int i =0;i<1000000;i++){
            match.matcher(STRING_ARR[i]).matches();
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void test_regFind(){
        for (int i =0;i<1000000;i++){
            find.matcher(STRING_ARR[i]).matches();
        }
    }

}

/**
 * Benchmark              Mode  Cnt   Score   Error  Units
 * Ext_Test.test_indexof  avgt   20   0.299 ± 0.032  ms/op
 * Ext_Test.test_regFind  avgt   20  56.558 ± 2.559  ms/op
* */

/**
 * Benchmark               Mode  Cnt    Score   Error  Units
 * Ext_Test.test_indexof   avgt   20    0.218 ± 0.006  ms/op
 * Ext_Test.test_regFind   avgt   20   59.516 ± 2.447  ms/op
 * Ext_Test.test_regMatch  avgt   20  140.552 ± 4.284  ms/op
 */
