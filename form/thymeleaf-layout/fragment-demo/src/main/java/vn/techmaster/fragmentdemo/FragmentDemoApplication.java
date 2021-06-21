package vn.techmaster.fragmentdemo;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class FragmentDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FragmentDemoApplication.class, args);
	}

	@RequestMapping("/")
	public String home(Map<String, Object> model) {
		model.put("user", new User("Thinh", "Tran"));
		return "my-page";
	}
}
