package jdk8.lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

public class LambdaTest3 {
    public static void main(String[] args) {
        MyMoney myMoney = new MyMoney(999999999);
//        myMoney.printMyMoney(money-> new DecimalFormat("#,###").format(money));
        Function<Integer, String>  function = money-> new DecimalFormat("#,###").format(money);
        function = function.andThen(s->"人民币："+s); //返回是function包装后的样子，实际上调用了两层Function的apply方法
        myMoney.printMyMoney(function); //我们可以不停地addThen,然后就可以分步骤进行操作，更加通俗易懂
        //还可以使用链式操作，对结果进行包装

    }
}
class MyMoney {
    private Integer money;

    MyMoney(Integer money) {
        this.money = money;
    }

    //使用jdk8中内置一些模板
    public void printMyMoney(Function<Integer, String> iMoneyFormat) {
        System.out.println("我的存款：" + iMoneyFormat.apply(this.money));

//    }
//    public void printMyMoney(IMoneyFormat iMoneyFormat){
//        System.out.println("我的存款："+iMoneyFormat.format(this.money));
//    }
    }
}
interface IMoneyFormat{
    public String format(Integer money);
}
