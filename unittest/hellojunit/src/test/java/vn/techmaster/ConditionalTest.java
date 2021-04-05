package vn.techmaster;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ConditionalTest {
  /*@Test
  @Disabled
  void neverRun() {
  }

  @Test
  @EnabledOnOs({ OS.LINUX, OS.MAC })
  void runOnSpecificOS() {
  }

  @Test
  @DisabledIfSystemProperty(named = "ci-server", matches = "true")
  void notOnCiServer() {
  }

  @Test
  @EnabledIfEnvironmentVariable(named = "ENV", matches = "test-env")
  void onlyOnTestServer() {
  }*/

  @ParameterizedTest(name = "Test fruit \"{0}\" with rank {1}")
  @CsvSource({"apple, 1", "banana, 2", "'lemon, lime', 3"})
  void testWithCsvSource(String fruit, int rank) {
    assertNotNull(fruit);
    assertNotEquals(0, rank);
  }
}
