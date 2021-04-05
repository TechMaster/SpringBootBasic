package main.java.vn.techmaster.withoutdi;

public class EmailService {
    public void sendEmail(String message, String receiver) {
        System.out.println("Email sent to " + receiver + " with Message=" + message);
    }
}
