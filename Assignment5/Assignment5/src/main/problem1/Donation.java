package problem1;

import java.time.LocalDateTime;

/**
 * Abstract base class representing a donation to a non-profit organization.
 * All donations have an amount and a creation date/time.
 * Subclasses define how their amount contributes to a given year's total.
 */
public abstract class Donation {

    protected double amount;
    protected LocalDateTime createdAt;

    public Donation(double amount, LocalDateTime createdAt) {
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public double getAmount() {
        return amount;
    }

    /**
     * Returns the total dollar amount this donation contributes to the given year.
     */
    public abstract double getAmountForYear(int year);
}
