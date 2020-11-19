package com.leo.share_mode.question;

class TicketWindow {
    private int count;
    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    //count是全局成员变量，而买票窗口共享一个count，当amount在返回之前，线程上下文切换时，会返回错误得amount
    //amount是局部变量，那么数据是不会错误，但是先后可能会出现错误
    public synchronized int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }
}
