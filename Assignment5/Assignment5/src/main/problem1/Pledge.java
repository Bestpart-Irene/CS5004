package problem1;

import java.time.LocalDateTime;

/**
 * Represents a pledge — a commitment to donate a specified amount at a future date.
 * The processing date is optional and may be set or removed later.
 * The pledge amount counts only toward the year it is processed.
 * If no processing date is set, the pledge contributes nothing to any year's total.
 */
public class Pledge extends Donation {

    private LocalDateTime processingDateTime;

    public Pledge(double amount, LocalDateTime createdAt) {
        super(amount, createdAt);
        this.processingDateTime = null;
    }

    public Pledge(double amount, LocalDateTime createdAt, LocalDateTime processingDateTime) {
        super(amount, createdAt);
        setProcessingDateTime(processingDateTime);
    }

    /**
     * Sets or updates the processing date/time for this pledge.
     *
     * @param processingDateTime the new processing date/time
     * @throws IllegalArgumentException if processingDateTime is before createdAt
     */
    public void setProcessingDateTime(LocalDateTime processingDateTime) {
        if (processingDateTime != null && processingDateTime.isBefore(createdAt)) {
            throw new IllegalArgumentException(
                "Processing date/time cannot be before the pledge creation date/time.");
        }
        this.processingDateTime = processingDateTime;
    }

    public LocalDateTime getProcessingDateTime() {
        return processingDateTime;
    }

    @Override
    public double getAmountForYear(int year) {
        if (processingDateTime == null) {
            return 0.0;
        }
        return processingDateTime.getYear() == year ? amount : 0.0;
    }
}
