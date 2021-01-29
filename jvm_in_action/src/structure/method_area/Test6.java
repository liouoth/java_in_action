package structure.method_area;


/**
 * 演示 StringTable 垃圾回收
 * PrintStringTableStatistics 可以看到stringtable中的数据信息
 * PrintGCDetails 可以看到垃圾回收的信息
 * --Xmx10m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
 */
public class Test6 {
    public static void main(String[] args) {
        int i = 0;
        try{
            for (int j=0;i<10000;j++){
                String.valueOf(i).intern();
                i++;
            }
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(i);
        }
    }
}
