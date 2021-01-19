package vn.techmaster.blog.testbase;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS) // để cho phương thức @BeforeAll chạy được
public abstract class PostgresqlTestBase {
  @Container
  private static PostgreSQLContainer<?> postgresqlContainer = 
  new PostgreSQLContainer<>("postgres:13.1")
  .withDatabaseName("foo")
  .withUsername("foo")
  .withPassword("secret");
  
  static { 
    postgresqlContainer.start();
    System.setProperty("DB_DRIVER_CLASS_NAME", "org.postgresql.Driver");
    System.setProperty("DB_URL", postgresqlContainer.getJdbcUrl());
    System.setProperty("DB_USERNAME", postgresqlContainer.getUsername());
    System.setProperty("DB_PASSWORD", postgresqlContainer.getPassword()); 
  }  
}
