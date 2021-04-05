package main.java.vn.techmaster.di.service;

public class SMSServiceImpl implements MessageService {
    @Override
    public void sendMessage(String msg, String rec) {
        System.out.println("SMS sent to " + rec + " with Message=" + msg);
    }
}
