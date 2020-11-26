package com.leo.nolock.question;

import java.util.concurrent.locks.ReentrantLock;

class AccountUnsafe implements Account {
    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public synchronized void withdraw(Integer amount) {
        balance -= amount;
    }

    public static void main(String[] args) {
        Account.demo(new AccountUnsafe(10000)); //接口中静态方法，直接传入对象，然后执行
    }
}