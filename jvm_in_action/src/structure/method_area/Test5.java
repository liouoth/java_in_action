package structure.method_area;

public class Test5 {
    public static void main(String[] args) {
        //例子1
        //new String("a")的时候，会不会将"a" 放入到串池中去
        //所以a.intern时，字符串中已经存在了"a",所以返回的b是"a"在StringTable返回地址，所以a不等于b
        String a = new String("a");
        String b = a.intern();
        System.out.println(a==b);

        //例子2
        //由于newString时，cb拼接是动态，所以cb不会存在在Stringtable中，但是c d存在
        String c = new String("c")+new String("d"); //StringTable ["c","d"]
        String d = "cd";
        String e = c.intern();
        System.out.println("cd"==e); //这个肯定是相等的
        System.out.println(d==e); //这个肯定是相等的

        //例子3
        //我觉得是正确的，因为intern会将对象在堆中的引用，放入到stringTable中去
        String f = new String("f")+new String("g");
        f.intern();
        String g = "fg";
        System.out.println(f==g);
    }
}
