package structure.direct_memory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test1 {
    static final String file_path = "C:\\Users\\mwqyy\\Downloads\\a.mp4";
    static final String file_target="E:\\a.mp4";

    static final int _1Mb = 1024*1024;

    public static void main(String[] args) {
        bio();
        directBuffer();
    }

    private static void directBuffer() {
        long start = System.nanoTime();

        try(
                FileChannel from = new FileInputStream(file_path).getChannel();
                FileChannel to = new FileOutputStream(file_target).getChannel();
                ){
            ByteBuffer byteBuffer = ByteBuffer.allocate(_1Mb);
            while (true){
                int len = from.read(byteBuffer);
                if (len == -1){
                    break;
                }
                byteBuffer.flip();
                to.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.nanoTime();

        System.out.println("directBuffer用时:"+(end-start)/1000_000.0);
    }

    private static void bio() {
        long start = System.nanoTime();
        try(
                FileInputStream from = new FileInputStream(file_path);
                FileOutputStream to = new FileOutputStream(file_target);
        ){
            byte [] byteBuffer = new byte[_1Mb];
            int i = 0;
            while ((i=from.read(byteBuffer))!=-1){
                to.write(byteBuffer,0,i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.nanoTime();

        System.out.println("bio用时:"+(end-start)/1000_000.0);
    }
}
