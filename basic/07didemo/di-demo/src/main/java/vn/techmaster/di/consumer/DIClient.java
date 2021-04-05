package main.java.vn.techmaster.di.consumer;

import main.java.vn.techmaster.di.service.MessageService;

public class DIClient implements Consumerable {
    private MessageService service;

    public DIClient() {
    }

    public DIClient(MessageService svc) {
        this.service = svc;
    }

    public void setService(MessageService service) {
        this.service = service;
    }

    @Override
    public void processMessages(String msg, String rec) {
        service.sendMessage(msg, rec);
    }

}
