package vn.techmaster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {
    private static Calculator calculator;

    @BeforeAll
    static void setUp() {
        System.out.println("Before All, set up thing and say Hi");
        calculator = new Calculator();
    }

    @AfterAll
    static void cleanUp() {
        System.out.println("After All, clean up and say good bye");
    }

    @DisplayName("Cộng hai số")
    @Test
    public void addTwoNumbers() {
        assertEquals(11, calculator.add(10, 1));
    }

    @DisplayName("Chia cho không")
    @Test
    public void divideByZeroJunit5() {
        assertThrows(ArithmeticException.class, () -> {
            calculator.devide(10, 0);
        });
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println("After Each");
    }
}
