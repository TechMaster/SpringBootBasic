package vn.techmaster;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

public class SampleTest {

  @BeforeAll
  static void initAll() {
    System.out.println("Init once at beginning");
  }

  @BeforeEach
  void init() {
    System.out.println("Run before every test");
  }

  @Test
  void succeedingTest() {
    assertTrue("abc".contains("a"));
  }

  @Test
  void failingTest() {
    fail("a failing test");
  }

  @Test
  @Disabled("for demonstration purposes")
  void skippedTest() {
    assertTrue("abc".contains("a"));
  }

  @Test
  @DisplayName("Huỷ bỏ")
  void abortedTest() {
    assumeTrue("abc".contains("Z"));
    fail("test should have been aborted");
  }

  @AfterEach
  void tearDown() {
  }

  @AfterAll
  static void tearDownAll() {
  }

}