package main.java.vn.techmaster.withoutdi;

public class ClientTest {
    public static void main(String[] args) {
		Client client = new Client();
		client.processMessages("Hi from not DI", "thinh@gmail.com");
	}
}
