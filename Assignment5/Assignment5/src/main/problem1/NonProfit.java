package problem1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a non-profit organization that tracks donations it has received.
 */
public class NonProfit {

    private String name;
    private List<Donation> donations;

    public NonProfit(String name) {
        this.name = name;
        this.donations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    public List<Donation> getDonations() {
        return new ArrayList<>(donations);
    }

    /**
     * Returns the total amount of donations processed in the given year.
     * Delegates to each donation's own year-calculation logic,
     * so new donation types can be added without modifying this class.
     *
     * @param year the calendar year to total
     * @return total dollar amount processed in that year
     */
    public double getTotalDonationsForYear(int year) {
        double total = 0.0;
        for (Donation d : donations) {
            total += d.getAmountForYear(year);
        }
        return total;
    }
}
