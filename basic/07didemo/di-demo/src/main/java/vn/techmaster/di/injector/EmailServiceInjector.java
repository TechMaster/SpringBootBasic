package main.java.vn.techmaster.di.injector;

import main.java.vn.techmaster.di.consumer.Consumerable;
import main.java.vn.techmaster.di.consumer.DIClient;
import main.java.vn.techmaster.di.service.EmailServiceImpl;

public class EmailServiceInjector implements MessageServiceInjector {
    @Override
	public Consumerable getConsumer() {
		DIClient client = new DIClient();
		client.setService(new EmailServiceImpl());
		return client;
	}
}
