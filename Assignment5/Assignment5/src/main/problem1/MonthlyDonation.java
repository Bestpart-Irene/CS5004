package problem1;

import java.time.LocalDateTime;
import java.time.YearMonth;

/**
 * Represents a recurring monthly donation that continues until cancelled.
 * Each month, on the same day and time as the creation date, the amount is donated.
 * A cancel date/time can be set; once set, no payments occur after that point.
 */
public class MonthlyDonation extends Donation {

    private LocalDateTime cancelDateTime;

    public MonthlyDonation(double amount, LocalDateTime createdAt) {
        super(amount, createdAt);
        this.cancelDateTime = null;
    }

    /**
     * Sets the cancellation date/time for this donation.
     *
     * @param cancelDateTime the date/time at which the donation is cancelled
     * @throws IllegalArgumentException if cancelDateTime is before createdAt
     */
    public void setCancelDateTime(LocalDateTime cancelDateTime) {
        if (cancelDateTime.isBefore(createdAt)) {
            throw new IllegalArgumentException(
                "Cancel date/time cannot be before the donation creation date/time.");
        }
        this.cancelDateTime = cancelDateTime;
    }

    public LocalDateTime getCancelDateTime() {
        return cancelDateTime;
    }

    /**
     * Counts how many monthly occurrences fall within the given year,
     * respecting the creation date and optional cancellation date.
     */
    @Override
    public double getAmountForYear(int year) {
        int count = 0;
        for (int month = 1; month <= 12; month++) {
            LocalDateTime occurrence = occurrenceInMonth(year, month);
            if (occurrence == null) continue;
            boolean afterOrOnCreation = !occurrence.isBefore(createdAt);
            boolean notCancelled = cancelDateTime == null || !occurrence.isAfter(cancelDateTime);
            if (afterOrOnCreation && notCancelled) {
                count++;
            }
        }
        return count * amount;
    }

    /**
     * Computes the occurrence datetime for a given year/month.
     * Uses the same day-of-month and time as createdAt, capped to the last day of the month.
     */
    private LocalDateTime occurrenceInMonth(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        int day = Math.min(createdAt.getDayOfMonth(), ym.lengthOfMonth());
        return LocalDateTime.of(year, month, day,
            createdAt.getHour(), createdAt.getMinute(), createdAt.getSecond());
    }
}
