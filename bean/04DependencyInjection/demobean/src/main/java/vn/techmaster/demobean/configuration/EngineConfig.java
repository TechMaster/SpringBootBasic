package vn.techmaster.demobean.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.techmaster.demobean.bean.Car;
import vn.techmaster.demobean.bean.Engine;


@Configuration
public class EngineConfig {
  @Autowired
  private ApplicationContext context;

  @Value("${engineType}")
  private String engineType;

  @Bean
  public Engine gasEngine() {
    return new Engine("Gas Engine");
  }

  @Bean(name = "electricEngine")
  public Engine teslaEngine() {
    return new Engine("Tesla Electric Engine");
  }

  @Bean(name = "hybridEngine")
  public Engine dongcoLai() {
    return new Engine("Hybrid Engine");
  }

  @Bean
  public Car betterCar() {
    Engine engine;
    switch (engineType) {
      case "gas":
        engine = (Engine) context.getBean("gasEngine");
        break;
      case "electric":
        engine = (Engine) context.getBean("electricEngine");
        break;
      case "hybrid":
        engine = (Engine) context.getBean("hybridEngine");
        break;
      default:
        engine = (Engine) context.getBean("gasEngine");
    }
    
    return new Car(engine);
  }
}
