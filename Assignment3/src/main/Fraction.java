/**
 * Interface representing a rational number (fraction).
 * Defines the protocol for immutable or mutable fraction implementations
 * that maintain a numerator and a strictly positive denominator.
 * 
 * Extends Comparable to allow sorting and comparison of fractions.
 */
public interface Fraction extends Comparable<Fraction> {

    /**
     * Gets the numerator of the fraction.
     * @return the integer numerator.
     */
    int getNumerator();

    /**
     * Gets the denominator of the fraction.
     * @return the positive integer denominator.
     */
    int getDenominator();

    /**
     * Sets the numerator.
     * After setting, the fraction should be normalized (reduced to simplest form).
     * @param n the new numerator.
     */
    void setNumerator(int n);

    /**
     * Sets the denominator.
     * The denominator must be a strictly positive integer.
     * After setting, the fraction should be normalized.
     * 
     * @param d the new denominator.
     * @throws IllegalArgumentException if d is less than or equal to 0.
     */
    void setDenominator(int d);

    /**
     * Returns the decimal representation of the fraction.
     * @return the double value approximation of the fraction.
     */
    double toDouble();

    /**
     * Returns a new Fraction that is the multiplicative inverse of this fraction.
     * If this fraction is a/b, the reciprocal is b/a.
     * 
     * @return a new Fraction representing the reciprocal.
     * @throws IllegalArgumentException if the numerator is 0 (division by zero).
     */
    Fraction reciprocal();

    /**
     * Adds another fraction to this fraction.
     * 
     * @param other the other Fraction to add.
     * @return a new Fraction representing the sum of this and other.
     */
    Fraction add(Fraction other);

    /**
     * Compares this fraction with the specified fraction for order.
     * 
     * @param other the fraction to be compared.
     * @return a negative integer, zero, or a positive integer as this fraction
     *         is less than, equal to, or greater than the specified fraction.
     */
    @Override
    int compareTo(Fraction other);
}