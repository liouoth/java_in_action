package com.leo.nolock.question;

import java.util.concurrent.atomic.AtomicInteger;

class AccountSafeUnlock implements Account {
    private AtomicInteger balance;

    public AccountSafeUnlock(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public synchronized void withdraw(Integer amount) {
//        while (true){
//            int prev = balance.get();
//            int next = prev - amount;
//            if (balance.compareAndSet(prev,next)){ //进行前后比较
//                break;
//            }
//        }
        balance.getAndAdd(-amount);
    }

    public static void main(String[] args) {
//        Account.demo(new AccountUnsafe(10000));
        Account.demo(new AccountSafeUnlock(10000)); //接口中静态方法，直接传入对象，然后执行
    }
}
