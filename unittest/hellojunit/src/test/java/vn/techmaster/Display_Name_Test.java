package vn.techmaster;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Display name Class Level")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Display_Name_Test {
  @Test

  void another_Test_Camel_Case() {
    assertTrue(1 < 2);
  }

  @DisplayName("Test parameters with nice names")
  @ParameterizedTest(name = "Use the value {0} for test")
  @ValueSource(ints = { -1, -4 })
  void isValidYear(int number) {
    assertTrue(number < 0);
  }


}
