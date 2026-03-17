package problem1;

import java.time.LocalDateTime;

/**
 * Represents a one-time donation made on a specific date.
 * The amount counts only toward the year in which the donation was created.
 */
public class OneTimeDonation extends Donation {

    public OneTimeDonation(double amount, LocalDateTime createdAt) {
        super(amount, createdAt);
    }

    @Override
    public double getAmountForYear(int year) {
        return createdAt.getYear() == year ? amount : 0.0;
    }
}
