package main.java.vn.techmaster.di.injector;

import main.java.vn.techmaster.di.consumer.Consumerable;
import main.java.vn.techmaster.di.consumer.DIClient;
import main.java.vn.techmaster.di.service.SMSServiceImpl;

public class SMSServiceInjector implements MessageServiceInjector {

    @Override
    public Consumerable getConsumer() {
        return new DIClient(new SMSServiceImpl());
    }
    
}
