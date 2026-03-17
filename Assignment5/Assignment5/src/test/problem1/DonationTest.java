package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DonationTest {

    private LocalDateTime feb10_2026;
    private LocalDateTime feb15_2026;
    private NonProfit org;

    @BeforeEach
    void setUp() {
        feb10_2026 = LocalDateTime.of(2026, 2, 10, 12, 0, 0);
        feb15_2026 = LocalDateTime.of(2026, 2, 15, 17, 45, 0);
        org = new NonProfit("Hope Foundation");
    }

    // ----- OneTimeDonation -----

    @Test
    void oneTimeDonation_countsInCreationYear() {
        OneTimeDonation d = new OneTimeDonation(100.0, feb10_2026);
        assertEquals(100.0, d.getAmountForYear(2026));
    }

    @Test
    void oneTimeDonation_zeroInOtherYear() {
        OneTimeDonation d = new OneTimeDonation(100.0, feb10_2026);
        assertEquals(0.0, d.getAmountForYear(2025));
        assertEquals(0.0, d.getAmountForYear(2027));
    }

    // ----- MonthlyDonation -----

    @Test
    void monthlyDonation_countsCorrectMonthsInCreationYear() {
        // Created Feb 10, 2026 — Feb through Dec = 11 payments
        MonthlyDonation d = new MonthlyDonation(10.0, feb10_2026);
        assertEquals(110.0, d.getAmountForYear(2026));
    }

    @Test
    void monthlyDonation_countsAllMonthsInFutureYear() {
        MonthlyDonation d = new MonthlyDonation(10.0, feb10_2026);
        assertEquals(120.0, d.getAmountForYear(2027));
    }

    @Test
    void monthlyDonation_zeroBeforeCreationYear() {
        MonthlyDonation d = new MonthlyDonation(10.0, feb10_2026);
        assertEquals(0.0, d.getAmountForYear(2025));
    }

    @Test
    void monthlyDonation_respectsCancelDate() {
        // Created Feb 15, 2026; cancelled April 15, 2026 → Feb, Mar, Apr = 3 payments
        MonthlyDonation d = new MonthlyDonation(25.0, feb15_2026);
        d.setCancelDateTime(LocalDateTime.of(2026, 4, 15, 17, 45, 0));
        assertEquals(75.0, d.getAmountForYear(2026));
    }

    @Test
    void monthlyDonation_cancelDateBeforeCreationThrows() {
        MonthlyDonation d = new MonthlyDonation(25.0, feb15_2026);
        assertThrows(IllegalArgumentException.class,
            () -> d.setCancelDateTime(LocalDateTime.of(2025, 1, 1, 0, 0, 0)));
    }

    @Test
    void monthlyDonation_cancelledBeforeYearCountsZero() {
        // Created and cancelled entirely in 2025 → 0 in 2026
        MonthlyDonation d = new MonthlyDonation(10.0,
            LocalDateTime.of(2025, 1, 1, 0, 0, 0));
        d.setCancelDateTime(LocalDateTime.of(2025, 6, 1, 0, 0, 0));
        assertEquals(0.0, d.getAmountForYear(2026));
    }

    // ----- Pledge -----

    @Test
    void pledge_noProcessingDate_zeroForAllYears() {
        Pledge p = new Pledge(500.0, feb10_2026);
        assertEquals(0.0, p.getAmountForYear(2026));
        assertEquals(0.0, p.getAmountForYear(2027));
    }

    @Test
    void pledge_withProcessingDate_countsInThatYear() {
        Pledge p = new Pledge(500.0, feb10_2026,
            LocalDateTime.of(2027, 6, 1, 0, 0, 0));
        assertEquals(0.0, p.getAmountForYear(2026));
        assertEquals(500.0, p.getAmountForYear(2027));
    }

    @Test
    void pledge_processingDateBeforeCreationThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Pledge(500.0, feb10_2026,
                LocalDateTime.of(2025, 1, 1, 0, 0, 0)));
    }

    @Test
    void pledge_canRemoveProcessingDate() {
        Pledge p = new Pledge(500.0, feb10_2026,
            LocalDateTime.of(2027, 6, 1, 0, 0, 0));
        p.setProcessingDateTime(null);
        assertEquals(0.0, p.getAmountForYear(2027));
    }

    // ----- NonProfit -----

    @Test
    void nonProfit_totalForYearCombinesDonationTypes() {
        org.addDonation(new OneTimeDonation(100.0, feb10_2026));          // +100 in 2026
        org.addDonation(new MonthlyDonation(10.0, feb10_2026));           // +110 in 2026
        org.addDonation(new Pledge(500.0, feb10_2026,
            LocalDateTime.of(2027, 1, 1, 0, 0, 0)));                     // 0 in 2026

        assertEquals(210.0, org.getTotalDonationsForYear(2026), 0.001);
        assertEquals(620.0, org.getTotalDonationsForYear(2027), 0.001);  // 500 pledge + 120 monthly
    }

    @Test
    void nonProfit_getName() {
        assertEquals("Hope Foundation", org.getName());
    }
}
