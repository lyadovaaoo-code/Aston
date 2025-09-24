package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {
    @Test
    void testCompareGreater() {
        assertEquals("5 больше 3", NumberComparator.compare(5, 3));
    }

    @Test
    void testCompareLess() {
        assertEquals("3 меньше 5", NumberComparator.compare(3, 5));
    }

    @Test
    void testCompareEqual() {
        assertEquals("Числа равны", NumberComparator.compare(5, 5));
    }
}
