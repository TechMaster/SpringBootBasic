package vn.techmaster.blog.testbase;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource("h2.properties")
@TestInstance(Lifecycle.PER_CLASS) // để cho phương thức @BeforeAll chạy được
public abstract class H2TestBase {
  
}