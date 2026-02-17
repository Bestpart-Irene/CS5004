import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FractionTest {

    // --- Constructor Tests ---

    @Test
    void testConstructor_NormalAndSimplification() {
        Fraction f = new FractionImpl(4, 2);
        assertEquals(2, f.getNumerator());
        assertEquals(1, f.getDenominator());
        assertEquals("2 / 1", f.toString());
    }

    @Test
    void testConstructor_NegativeNumerator() {
        Fraction f = new FractionImpl(-1, 2);
        assertEquals(-1, f.getNumerator());
        assertEquals(2, f.getDenominator());
        assertEquals("-1 / 2", f.toString());
    }

    @Test
    void testConstructor_NegativeDenominator() {
        // Should move sign to numerator
        Fraction f = new FractionImpl(1, -2);
        assertEquals(-1, f.getNumerator());
        assertEquals(2, f.getDenominator());
    }

    @Test
    void testConstructor_DoubleNegative() {
        // -1 / -2 -> 1 / 2
        Fraction f = new FractionImpl(-1, -2);
        assertEquals(1, f.getNumerator());
        assertEquals(2, f.getDenominator());
    }

    @Test
    void testConstructor_ZeroNumerator() {
        Fraction f = new FractionImpl(0, 5);
        assertEquals(0, f.getNumerator());
        assertEquals(1, f.getDenominator(), "Zero should be normalized to 0/1");
    }

    @Test
    void testConstructor_ZeroDenominator_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FractionImpl(1, 0);
        });
    }

    // --- Getter / Setter Tests ---

    @Test
    void testSetNumerator_ReducesCorrectly() {
        Fraction f = new FractionImpl(1, 4);
        f.setNumerator(2); // Becomes 2/4 -> 1/2
        assertEquals(1, f.getNumerator());
        assertEquals(2, f.getDenominator());
    }

    @Test
    void testSetDenominator_ReducesCorrectly() {
        Fraction f = new FractionImpl(2, 3);
        f.setDenominator(6); // Becomes 2/6 -> 1/3
        assertEquals(1, f.getNumerator());
        assertEquals(3, f.getDenominator());
    }

    @Test
    void testSetDenominator_Negative_ThrowsException() {
        Fraction f = new FractionImpl(1, 2);
        assertThrows(IllegalArgumentException.class, () -> {
            f.setDenominator(-5);
        });
        // Ensure invariant preserved
        assertEquals(2, f.getDenominator());
    }

    @Test
    void testSetDenominator_Zero_ThrowsException() {
        Fraction f = new FractionImpl(1, 2);
        assertThrows(IllegalArgumentException.class, () -> {
            f.setDenominator(0);
        });
    }

    // --- Method Tests ---

    @Test
    void testToDouble() {
        Fraction f = new FractionImpl(1, 2);
        assertEquals(0.5, f.toDouble(), 0.0001);
        
        Fraction f2 = new FractionImpl(1, 3);
        assertEquals(0.3333, f2.toDouble(), 0.0001);
    }

    @Test
    void testToString() {
        Fraction f = new FractionImpl(4, 8);
        assertEquals("1 / 2", f.toString());
        
        Fraction f2 = new FractionImpl(-6, 3);
        assertEquals("-2 / 1", f2.toString());
    }

    @Test
    void testReciprocal() {
        Fraction f = new FractionImpl(2, 3);
        Fraction r = f.reciprocal();
        assertEquals(3, r.getNumerator());
        assertEquals(2, r.getDenominator());
    }

    @Test
    void testReciprocal_Negative() {
        Fraction f = new FractionImpl(-2, 3);
        Fraction r = f.reciprocal();
        // Reciprocal of -2/3 is -3/2
        assertEquals(-3, r.getNumerator());
        assertEquals(2, r.getDenominator());
    }

    @Test
    void testReciprocal_Zero_ThrowsException() {
        Fraction f = new FractionImpl(0, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            f.reciprocal();
        });
    }

    @Test
    void testAdd_CommonDenominator() {
        Fraction f1 = new FractionImpl(1, 4);
        Fraction f2 = new FractionImpl(2, 4);
        Fraction result = f1.add(f2); // 3/4
        assertEquals(3, result.getNumerator());
        assertEquals(4, result.getDenominator());
    }

    @Test
    void testAdd_DifferentDenominator() {
        Fraction f1 = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl(1, 3);
        Fraction result = f1.add(f2); // 3/6 + 2/6 = 5/6
        assertEquals(5, result.getNumerator());
        assertEquals(6, result.getDenominator());
    }

    @Test
    void testAdd_WithNegatives() {
        Fraction f1 = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl(-1, 2);
        Fraction result = f1.add(f2); // 0
        assertEquals(0, result.getNumerator());
    }

    @Test
    void testCompareTo() {
        Fraction f1 = new FractionImpl(1, 2);
        Fraction f2 = new FractionImpl(1, 3);
        Fraction f3 = new FractionImpl(2, 4); // Equal to f1
        Fraction f4 = new FractionImpl(-1, 2);

        // 1/2 > 1/3
        assertTrue(f1.compareTo(f2) > 0);
        // 1/3 < 1/2
        assertTrue(f2.compareTo(f1) < 0);
        // 1/2 == 2/4
        assertEquals(0, f1.compareTo(f3));
        // Positive > Negative
        assertTrue(f1.compareTo(f4) > 0);
        // Negative < Positive
        assertTrue(f4.compareTo(f1) < 0);
    }
}