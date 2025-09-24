package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticCalculatorTest {
    @Test
    void testAdd() {
        assertEquals(8, ArithmeticCalculator.add(5, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(2, ArithmeticCalculator.subtract(5, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(15, ArithmeticCalculator.multiply(5, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, ArithmeticCalculator.divide(6, 3));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            ArithmeticCalculator.divide(5, 0);
        });
    }
}
