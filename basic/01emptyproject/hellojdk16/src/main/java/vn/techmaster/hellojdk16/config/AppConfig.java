package vn.techmaster.hellojdk16.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.techmaster.hellojdk16.Hellojdk16Application;

@Configuration
public class AppConfig {
	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger(Hellojdk16Application.class);
	}
}
