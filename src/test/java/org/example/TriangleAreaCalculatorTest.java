package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaCalculatorTest {
    @Test
    void testCalculateArea() {
        assertEquals(25.0, TriangleAreaCalculator.calculateArea(10, 5));
    }

    @Test
    void testNegativeSides() {
        assertThrows(IllegalArgumentException.class, () -> {
            TriangleAreaCalculator.calculateArea(-10, 5);
        });
    }
}
