package jdk8.function;

import java.time.LocalDateTime;
import java.util.function.Supplier;

/**
 * {@link java.util.function.Supplier}
 * 生产者函数：不要求每一次调用都产生新的result
 */
public class SupplierTest {

    public static void supplierTimestampTest(){
        Supplier<LocalDateTime> dateTimeSupplier = ()->LocalDateTime.now();
        System.out.println(dateTimeSupplier.get());
    }

    public static void main(String[] args) {
        supplierTimestampTest();
    }
}
