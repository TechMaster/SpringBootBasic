package vn.techmaster.blog.testbase;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS) // để cho phương thức @BeforeAll chạy được
public abstract class MySQLTestBase {
  @Container
  private static MySQLContainer<?> container = 
  new MySQLContainer<>("mysql:8")
  .withDatabaseName("foo")
  .withUsername("foo")
  .withPassword("secret");
  
  static { 
    container.start();
    System.setProperty("DB_DRIVER_CLASS_NAME", "com.mysql.cj.jdbc.Driver");
    System.setProperty("DB_URL", container.getJdbcUrl());
    System.setProperty("DB_USERNAME", container.getUsername());
    System.setProperty("DB_PASSWORD", container.getPassword()); 
  }  
}