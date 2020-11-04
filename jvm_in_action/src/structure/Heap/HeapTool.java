package structure.Heap;

public class HeapTool {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Thread.sleep(30000);
        byte [] array = new byte[1024*2014*10];
        System.out.println("middle");
        Thread.sleep(30000);
        array=null;
        System.gc();
        System.out.println("end");
        Thread.sleep(100000L);
    }
}
