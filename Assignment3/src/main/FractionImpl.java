/**
 * Concrete implementation of the Fraction interface.
 * Represents a fraction with an integer numerator and a strictly positive denominator.
 * The fraction is always stored in its simplest form (reduced via GCD) 
 * with sign information carried by the numerator.
 */
public class FractionImpl implements Fraction {

    private int numerator;
    private int denominator;

    /**
     * Constructs a FractionImpl with the given numerator and denominator.
     * The fraction is automatically reduced to simplest form.
     * If the denominator is negative, the sign is moved to the numerator.
     * 
     * @param numerator   the numerator.
     * @param denominator the denominator (must not be zero).
     * @throws IllegalArgumentException if denominator is zero.
     */
    public FractionImpl(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        
        // Handle negative denominator normalization immediately for the raw values
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
        
        // Normalize reduces the fraction via GCD
        normalize();
    }

    /**
     * Helper method to reduce the fraction to simplest form using GCD.
     * This ensures the invariant that the fraction is always reduced.
     */
    private void normalize() {
        if (this.denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
        
        // Euclid's algorithm for GCD
        int gcd = gcd(Math.abs(this.numerator), this.denominator);
        
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    /**
     * Recursive implementation of Euclid's algorithm.
     * @param a first integer (non-negative)
     * @param b second integer (non-negative)
     * @return greatest common divisor
     */
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    @Override
    public int getNumerator() {
        return this.numerator;
    }

    @Override
    public int getDenominator() {
        return this.denominator;
    }

    @Override
    public void setNumerator(int n) {
        this.numerator = n;
        normalize();
    }

    @Override
    public void setDenominator(int d) {
        if (d <= 0) {
            throw new IllegalArgumentException("Denominator must be positive.");
        }
        this.denominator = d;
        normalize();
    }

    @Override
    public double toDouble() {
        return (double) this.numerator / this.denominator;
    }

    @Override
    public Fraction reciprocal() {
        if (this.numerator == 0) {
            throw new IllegalArgumentException("Cannot take reciprocal of zero.");
        }
        // Create new fraction with swapped values. 
        // Constructor handles sign normalization if numerator was negative.
        return new FractionImpl(this.denominator, this.numerator);
    }

    @Override
    public Fraction add(Fraction other) {
        // a/b + c/d = (ad + bc) / bd
        // Note: Check for potential integer overflow in real-world high-precision apps,
        // but standard int math is assumed here per prompt.
        int commonDenominator = this.denominator * other.getDenominator();
        int newNumerator = (this.numerator * other.getDenominator()) + 
                           (other.getNumerator() * this.denominator);
        
        return new FractionImpl(newNumerator, commonDenominator);
    }

    @Override
    public int compareTo(Fraction other) {
        // Compare a/b and c/d by comparing ad and bc.
        // Cast to long to prevent overflow during intermediate calculation
        long ad = (long) this.numerator * other.getDenominator();
        long bc = (long) other.getNumerator() * this.denominator;
        
        return Long.compare(ad, bc);
    }

    /**
     * Returns a string representation of the fraction in simplest form.
     * Format: "numerator / denominator"
     */
    @Override
    public String toString() {
        return this.numerator + " / " + this.denominator;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction)) return false;
        Fraction other = (Fraction) o;
        // Since we always normalize, direct comparison works
        return this.numerator == other.getNumerator() && 
               this.denominator == other.getDenominator();
    }
    
    @Override
    public int hashCode() {
        // Simple hash code generation
        return 31 * numerator + denominator;
    }
}