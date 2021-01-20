package vn.techmaster.demofilemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import vn.techmaster.demofilemanager.config.StorageProperties;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class DemoFileManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFileManagerApplication.class, args);
	}

}
