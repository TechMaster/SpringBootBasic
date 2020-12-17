package vn.techmaster.demobean.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.techmaster.demobean.bean.AutoSteering;
import vn.techmaster.demobean.bean.ManualSteering;

@Configuration
public class SteeringConfig {
  @Bean
  public AutoSteering autosteering() {
    return new AutoSteering();
  }

  @Bean
   public ManualSteering manualsteering() {
    return new ManualSteering();
  }
}
