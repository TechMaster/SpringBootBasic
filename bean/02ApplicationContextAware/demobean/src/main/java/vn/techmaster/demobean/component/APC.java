package vn.techmaster.demobean.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class APC implements ApplicationContextAware {

  @Autowired
  private ApplicationContext applicationContext1; // Lấy application context
  private ApplicationContext applicationContext2;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    applicationContext2 =  applicationContext;
  }

  public String doSomething() {
    StringBuilder str = new StringBuilder(applicationContext1.getDisplayName());
    str.append(" ");
    str.append(applicationContext2.getDisplayName());
    return str.toString();
  }  
}

/*
Khác biệt giữa @Component và @Bean
@Component is a class level annotation whereas @Bean is a method level annotation 
and name of the method serves as the bean name. 
@Component need not to be used with the @Configuration annotation 
where as @Bean annotation has to be used within the class which is annotated with @Configuration
*/