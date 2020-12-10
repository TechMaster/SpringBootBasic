package vn.techmaster.demobean.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import vn.techmaster.demobean.bean.Bar;
import vn.techmaster.demobean.bean.DBRepo;
import vn.techmaster.demobean.bean.Foo;
import vn.techmaster.demobean.bean.MySQLRepo;
import vn.techmaster.demobean.bean.OracleRepo;
import vn.techmaster.demobean.bean.Stone;

@Configuration
public class AppConfig {
  @Bean
  public Foo foo() {
    return new Foo("Foo is initiated from @Bean");
  }

  @Bean(name = "foo1")
  public Foo foo1() {
    return new Foo("Foo1");
  }

  @Bean(name = "foo2")
  public Foo foo2() {
    return new Foo("Foo2");
  }

  @Bean
  public Bar bar() {
    return new Bar();
  }

  @Bean
  @Scope("prototype") // mỗi lần được gọi sẽ tạo mới instance
  //@Scope("singleton") // Chỉ tạo duy nhất một instance
  public Stone stone() {
    return new Stone();
  }

  @Value("${database}")
  private String database;
  
  @Bean
  public DBRepo dbRepo() {
    if (database != null && database.equals("MYSQL")){
      return new MySQLRepo();   
    }
    return new OracleRepo();
  }
}
