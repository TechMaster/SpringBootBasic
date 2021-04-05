package main.java.vn.techmaster.withoutdi;

public class Client {

    private EmailService email = new EmailService();

    public void processMessages(String msg, String rec) {
        email.sendEmail(msg, rec);
    }
}
