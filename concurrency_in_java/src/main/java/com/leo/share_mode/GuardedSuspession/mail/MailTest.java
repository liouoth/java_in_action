package com.leo.share_mode.GuardedSuspession.mail;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MailTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<3;i++){
            new People().start();
        }
        Thread.sleep(1000);
        for (Integer id:MailBox.getIds()){
            new Postman(id,"小黄片"+id).start();
        }
    }
}
@Slf4j
class People extends Thread{
    @Override
    public void run() {
        int id = MailBox.generateBox();
        log.debug("创建了信箱：" +id);
        log.debug("收信："+MailBox.get(id,5000));
    }
}
@Slf4j
class Postman extends Thread{
    //暂定一个邮差一封信
    private int id;
    private String mail;
    Postman(int id,String mail){
        this.id = id;
        this.mail = mail;
    }
    @Override
    public void run() {
        log.debug("开始送信："+mail);
        MailBox.put(this.id,this.mail);
    }
}