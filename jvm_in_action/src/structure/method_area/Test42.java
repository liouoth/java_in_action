package structure.method_area;

public class Test42 {
    public static void main(String[] args) {
//        String a = "ab";
//        String b = new String("ab").intern();
//        System.out.println(a==b);
        System.out.println(foo());
    }

    public static int foo(){
        try{
            return 2;
        }catch (Exception e){

        }finally {
            return 1;
        }
    }
}
