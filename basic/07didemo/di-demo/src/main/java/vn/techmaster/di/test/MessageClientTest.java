package main.java.vn.techmaster.di.test;

import main.java.vn.techmaster.di.consumer.Consumerable;
import main.java.vn.techmaster.di.injector.EmailServiceInjector;
import main.java.vn.techmaster.di.injector.MessageServiceInjector;
import main.java.vn.techmaster.di.injector.SMSServiceInjector;

public class MessageClientTest {
    public static void main(String[] args) {
        String msg = "Hi from DI client";
        String email = "thinh@gmail.com";
        String phone = "0123456789";
        MessageServiceInjector injector = null;
        Consumerable client = null;

        // Send email
        injector = new EmailServiceInjector();
        client = injector.getConsumer();
        client.processMessages(msg, email);

        // Send SMS
        injector = new SMSServiceInjector();
        client = injector.getConsumer();
        client.processMessages(msg, phone);
    }
}
