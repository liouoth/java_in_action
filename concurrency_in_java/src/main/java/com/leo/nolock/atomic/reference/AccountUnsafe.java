package com.leo.nolock.atomic.reference;


import java.util.concurrent.atomic.AtomicReference;

class AccountSafeReference implements Account {
    private AtomicReference<Integer> balance;

    public AccountSafeReference(Integer balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public synchronized void withdraw(Integer amount) {
        Integer prev,next;
        do{
            prev = balance.get();
            next = prev - amount;
        }while (!balance.compareAndSet(prev,next));
    }

    public static void main(String[] args) {
        Account.demo(new AccountSafeReference(10000)); //接口中静态方法，直接传入对象，然后执行
    }
}